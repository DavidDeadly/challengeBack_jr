package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.DomainRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.commands.CreateInventory;
import co.sofka.challenge_jr.domain.events.InventoryCreated;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreateInventoryUseCaseTest {
  @Mock
  private DomainRepository repository;

  @InjectMocks
  private CreateInventoryUseCase useCase;

  @Test
  void createInventoryUseCase() {

    final String inventoryName = "sofka";
    CreateInventory createInventoryCom = new CreateInventory("1", inventoryName);

    InventoryCreated inventoryCreated = new InventoryCreated(inventoryName);

    BDDMockito.when(repository.saveView(BDDMockito.any(Inventory.class)))
            .thenReturn(Mono.empty());

    BDDMockito.when(repository.saveEvent(BDDMockito.any(DomainEvent.class)))
            .thenReturn(Mono.just(inventoryCreated));

    Mono<List<DomainEvent>> listEvents = useCase.applyCommand(createInventoryCom)
            .collectList();

    StepVerifier
            .create(listEvents)
            .assertNext(events -> {
              InventoryCreated createEvent = (InventoryCreated) events.get(0);

              assertEquals(1, events.size());
              assertEquals(createEvent.getInventoryName(), inventoryName);
            })
            .expectComplete()
            .log()
            .verify();

    BDDMockito.verify(repository).saveEvent(BDDMockito.any(DomainEvent.class));
    BDDMockito.verify(repository).saveView(BDDMockito.any(Inventory.class));
  }

}