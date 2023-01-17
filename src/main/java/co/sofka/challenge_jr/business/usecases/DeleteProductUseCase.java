package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.CommandExecutor;
import co.sofka.challenge_jr.business.gateways.DomainEventRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.commands.DeleteProduct;
import co.sofka.challenge_jr.domain.values.InventoryID;
import co.sofka.challenge_jr.domain.values.ProductID;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class DeleteProductUseCase implements CommandExecutor<DeleteProduct> {
  private final DomainEventRepository repository;

  public DeleteProductUseCase(DomainEventRepository repository) {
    this.repository = repository;
  }

  @Override
  public Flux<DomainEvent> applyCommand(DeleteProduct deleteProductCommand) {
    return Mono.just(deleteProductCommand)
            .flatMapMany(command ->
                repository
                .findById(command.getInventoryID())
                .collectList()
                .flatMapIterable(events -> {
                  Inventory inventory = Inventory.from(
                          new InventoryID(command.getInventoryID()),
                          events
                  );

                  inventory.deleteProduct(
                          new ProductID(command.getProductID())
                  );

                  return inventory.getUncommittedChanges();
                }))
            .flatMap(repository::saveEvent);
  }
}
