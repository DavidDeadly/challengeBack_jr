package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.DomainRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.commands.AddProduct;
import co.sofka.challenge_jr.domain.events.InventoryCreated;
import co.sofka.challenge_jr.domain.events.ProductAdded;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AddProductUseCaseTest {
  public static final String INVENTORY_ID = "1";
  @Mock
  private DomainRepository repository;

  @InjectMocks
  private AddProductUseCase useCase;

  @Test
  void addProductUseCase() {
    AddProduct addProductCom = new AddProduct(INVENTORY_ID, "PC", 500, true, 50, 1);

    InventoryCreated inventoryCreated = new InventoryCreated("sofka");
    ProductAdded productAdded = new ProductAdded(
            addProductCom.getProductID(),
            addProductCom.getName(),
            addProductCom.getInInventory(),
            addProductCom.getEnabled(),
            addProductCom.getMin(),
            addProductCom.getMax()
    );

    BDDMockito.when(repository.findById(BDDMockito.anyString()))
              .thenReturn(Flux.just(inventoryCreated));

    BDDMockito.when(repository.saveView(BDDMockito.any(Inventory.class)))
            .thenReturn(Mono.empty());

    BDDMockito.when(repository.saveEvent(BDDMockito.any(DomainEvent.class)))
            .thenReturn(Mono.just(productAdded));

    Mono<List<DomainEvent>> listEvents = useCase.applyCommand(addProductCom)
            .collectList();

    StepVerifier
            .create(listEvents)
            .assertNext(events -> {
              ProductAdded event = (ProductAdded) events.get(0);

              assertEquals(1, events.size());
              assertEquals(addProductCom.getProductID(), event.getProductID());
              assertEquals(addProductCom.getName(), event.getName());
            })
            .expectComplete()
            .log()
            .verify();

    BDDMockito.verify(repository).findById(BDDMockito.anyString());
    BDDMockito.verify(repository).saveEvent(BDDMockito.any(DomainEvent.class));
    BDDMockito.verify(repository).saveView(BDDMockito.any(Inventory.class));
  }
}