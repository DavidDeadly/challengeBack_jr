package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.DomainRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.commands.UpdateProduct;
import co.sofka.challenge_jr.domain.events.*;
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
class UpdateProductUseCaseTest {
  public static final String INVENTORY_ID = "1";
  public static final String PRODUCT_ID = "1";
  @Mock
  private DomainRepository repository;

  @InjectMocks
  private UpdateProductUseCase useCase;

  @Test
  void updateProductUseCase() {

    UpdateProduct updateProductCom = new UpdateProduct(
            INVENTORY_ID,
            PRODUCT_ID,
            500,
            "PC",
            2,
            50
    );

    InventoryCreated inventoryCreated = new InventoryCreated("sofka");
    ProductAdded productAdded = new ProductAdded(PRODUCT_ID, "LAPTOP", 50, true, 1, 10);
    ProductRenamed productRenamed = new ProductRenamed(PRODUCT_ID, updateProductCom.getName());
    InventoryProductUpdated inventoryProductUpdated = new InventoryProductUpdated(PRODUCT_ID, updateProductCom.getInInventory());
    ProductMinUpdated productMinUpdated = new ProductMinUpdated(PRODUCT_ID, updateProductCom.getMin());
    ProductMaxUpdated productMaxUpdated = new ProductMaxUpdated(PRODUCT_ID, updateProductCom.getMax());

    BDDMockito.when(repository.findById(BDDMockito.anyString()))
            .thenReturn(Flux.just(inventoryCreated, productAdded));

    BDDMockito.when(repository.saveView(BDDMockito.any(Inventory.class)))
            .thenReturn(Mono.empty());

    BDDMockito.when(repository.saveEvent(BDDMockito.any(ProductRenamed.class)))
            .thenReturn(Mono.just(productRenamed));

    BDDMockito.when(repository.saveEvent(BDDMockito.any(InventoryProductUpdated.class)))
            .thenReturn(Mono.just(inventoryProductUpdated));

    BDDMockito.when(repository.saveEvent(BDDMockito.any(ProductMinUpdated.class)))
            .thenReturn(Mono.just(productMinUpdated));

    BDDMockito.when(repository.saveEvent(BDDMockito.any(ProductMaxUpdated.class)))
            .thenReturn(Mono.just(productMaxUpdated));

    Mono<List<DomainEvent>> listEvents = useCase.applyCommand(updateProductCom)
            .collectList();

    StepVerifier
      .create(listEvents)
      .assertNext(events -> {

        assertEquals(4, events.size());
        assertInstanceOf(ProductRenamed.class, events.get(0));
        assertInstanceOf(InventoryProductUpdated.class, events.get(1));
        assertInstanceOf(ProductMinUpdated.class, events.get(2));
        assertInstanceOf(ProductMaxUpdated.class, events.get(3));
      })
      .expectComplete()
      .log()
      .verify();

    BDDMockito.verify(repository).findById(BDDMockito.anyString());
    BDDMockito.verify(repository).saveView(BDDMockito.any(Inventory.class));
    BDDMockito.verify(repository).saveEvent(BDDMockito.any(ProductRenamed.class));
    BDDMockito.verify(repository).saveEvent(BDDMockito.any(InventoryProductUpdated.class));
    BDDMockito.verify(repository).saveEvent(BDDMockito.any(ProductMinUpdated.class));
    BDDMockito.verify(repository).saveEvent(BDDMockito.any(ProductMaxUpdated.class));
  }
}