package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.CommandExecutor;
import co.sofka.challenge_jr.business.gateways.DomainEventRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.commands.AddProduct;
import co.sofka.challenge_jr.domain.values.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class AddProductUseCase implements CommandExecutor<AddProduct> {
  private final DomainEventRepository repository;

  public AddProductUseCase(DomainEventRepository repository) {
    this.repository = repository;
  }

  @Override
  public Flux<DomainEvent> applyCommand(AddProduct addProductCommand) {
    return Mono.just(addProductCommand)
            .flatMapMany(command ->
                repository.findById(command.getInventoryID())
                .collectList()
                .flatMapIterable(events -> {
                  Inventory inventory = Inventory.from(
                          new InventoryID(command.getInventoryID()),
                          events
                  );

                  inventory.addProduct(
                          new ProductID(command.getProductID()),
                          new Name(command.getName()),
                          new InInventory(command.getInInventory()),
                          new Enabled(command.getEnabled()),
                          new Min(command.getMin()),
                          new Max(command.getMax())
                  );

                  return inventory.getUncommittedChanges();
                }))
            .flatMap(repository::saveEvent);
  }
}
