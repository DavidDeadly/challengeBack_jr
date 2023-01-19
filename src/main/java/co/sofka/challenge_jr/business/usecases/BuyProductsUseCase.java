package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.CommandExecutor;
import co.sofka.challenge_jr.business.gateways.DomainRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.commands.BuyProducts;
import co.sofka.challenge_jr.domain.values.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BuyProductsUseCase implements CommandExecutor<BuyProducts> {
  private final DomainRepository repository;

  public BuyProductsUseCase(DomainRepository repository) {
    this.repository = repository;
  }

  @Override
  public Flux<DomainEvent> applyCommand(BuyProducts buyProductsCommand) {
    return Mono.just(buyProductsCommand)
            .flatMapMany(command ->
                repository
                .findById(command.getInventoryID())
                .collectList()
                .flatMapIterable(events -> {
                  Inventory inventory = Inventory.from(
                          new InventoryID(command.getInventoryID()),
                          events
                  );

                  Set<ProductsBuy> productsBuys = command.getProductsBuy().stream().map(productsBuy ->
                          new ProductsBuy(
                                  productsBuy.getProductId(),
                                  productsBuy.getQuantity()
                          )
                  ).collect(Collectors.toSet());

                  inventory.buyProducts(
                          productsBuys,
                          new ClientName(command.getClientName()),
                          new IDType(IDTypeEnum.valueOf(command.getIdType())),
                          new IDClient(command.getIdClient())
                  );

                  new Thread(() -> repository.saveView(inventory).subscribe()).start();

                  return inventory.getUncommittedChanges();
                }))
            .flatMap(repository::saveEvent);
  }
}
