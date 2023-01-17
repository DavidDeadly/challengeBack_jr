package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.CommandExecutor;
import co.sofka.challenge_jr.business.gateways.DomainEventRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.Product;
import co.sofka.challenge_jr.domain.commands.UpdateProduct;
import co.sofka.challenge_jr.domain.values.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class UpdateProductUseCase implements CommandExecutor<UpdateProduct> {
  private final DomainEventRepository repository;

  public UpdateProductUseCase(DomainEventRepository repository) {
    this.repository = repository;
  }

  @Override
  public Flux<DomainEvent> applyCommand(UpdateProduct updateProductCommand) {
    return Mono.just(updateProductCommand)
            .flatMapMany(command ->
                    repository
                    .findById(command.getInventoryID())
                    .collectList()
                    .flatMapIterable(events -> {
                      Inventory inventory = Inventory.from(
                              new InventoryID(command.getInventoryID()),
                              events
                      );

                      final ProductID productId = new ProductID(command.getProductID());
                      Optional<Product> productById = inventory.getProductById(productId);

                      productById.ifPresent(product -> {
                        final String name = command.getName();
                        final Integer inInventory = command.getInInventory();
                        final Integer min = command.getMin();
                        final Integer max = command.getMax();

                        if(!product.Name().value().equals(name)) {
                          inventory.renameProduct(productId, new Name(name));
                        }

                        if(!product.InInventory().value().equals(inInventory)) {
                          inventory.updateProductInventory(productId, new InInventory(inInventory));
                        }

                        if(!product.Min().value().equals(min)) {
                          inventory.updateProductMin(productId, new Min(min));
                        }

                        if(!product.Max().value().equals(max)) {
                          inventory.updateProductMax(productId, new Max(max));
                        }
                      });

                      return inventory.getUncommittedChanges();
                    }))
            .flatMap(repository::saveEvent);
  }
}
