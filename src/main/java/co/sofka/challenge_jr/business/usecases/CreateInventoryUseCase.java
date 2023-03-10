package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.CommandExecutor;
import co.sofka.challenge_jr.business.gateways.DomainRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.commands.CreateInventory;
import co.sofka.challenge_jr.domain.values.InventoryID;
import co.sofka.challenge_jr.domain.values.Name;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CreateInventoryUseCase implements CommandExecutor<CreateInventory> {
  private final DomainRepository repository;

  public CreateInventoryUseCase(DomainRepository repository) {
    this.repository = repository;
  }


  @Override
  public Flux<DomainEvent> applyCommand(CreateInventory createInventoryCommand) {
    return Mono.just(createInventoryCommand)
            .flatMapIterable(command -> {
              Inventory inventory = new Inventory(
                      new InventoryID(command.getInventoryId()),
                      new Name(command.getName())
              );

              new Thread(() -> repository.saveView(inventory).subscribe()).start();

              return inventory.getUncommittedChanges();
            })
            .flatMap(repository::saveEvent);
  }
}
