package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.CommandExecutor;
import co.sofka.challenge_jr.business.gateways.DomainRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.Product;
import co.sofka.challenge_jr.domain.commands.UpdateProduct;
import co.sofka.challenge_jr.domain.values.*;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class UpdateProductUseCase implements CommandExecutor<UpdateProduct> {
  private final DomainRepository repository;

  public UpdateProductUseCase(DomainRepository repository) {
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
                      Optional<Product> productById = inventory.getProductById(productId.value());

                      productById.ifPresent(product -> {
                        final String name = command.getName();
                        final Integer inInventory = command.getInInventory();
                        final Integer min = command.getMin();
                        final Integer max = command.getMax();

                        if(!product.Name().value().equals(name) && name != null) {
                          inventory.renameProduct(productId, new Name(name));
                        }

                        if(!product.InInventory().value().equals(inInventory) && inInventory != null) {
                          inventory.updateProductInventory(productId, new InInventory(inInventory));
                        }

                        if(!product.Min().value().equals(min) && min != null) {
                          inventory.updateProductMin(productId, new Min(min));
                        }

                        if(!product.Max().value().equals(max) && max != null) {
                          inventory.updateProductMax(productId, new Max(max));
                        }
                      });

                      new Thread(() -> repository.saveView(inventory).subscribe()).start();

                      return inventory.getUncommittedChanges();
                    }))
            .flatMap(repository::saveEvent);
  }
}
