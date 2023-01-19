package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.DomainRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.commands.DeleteProduct;
import co.sofka.challenge_jr.domain.events.InventoryCreated;
import co.sofka.challenge_jr.domain.events.ProductAdded;
import co.sofka.challenge_jr.domain.events.ProductDeleted;
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
class DeleteProductUseCaseTest {
  public static final String INVENTORY_ID = "1";
  public static final String PRODUCT_ID = "1";
  @Mock
  private DomainRepository repository;

  @InjectMocks
  private DeleteProductUseCase useCase;

  @Test
  void deleteProductUseCase() {
    DeleteProduct deleteProductCom = new DeleteProduct(INVENTORY_ID, PRODUCT_ID);

    InventoryCreated inventoryCreated = new InventoryCreated("sofka");
    ProductAdded productAdded = new ProductAdded("PC", 500, true, 50, 1);
    ProductDeleted productDeleted = new ProductDeleted(PRODUCT_ID);


    BDDMockito.when(repository.findById(BDDMockito.anyString()))
            .thenReturn(Flux.just(inventoryCreated, productAdded));

    BDDMockito.when(repository.saveView(BDDMockito.any(Inventory.class)))
            .thenReturn(Mono.empty());

    BDDMockito.when(repository.saveEvent(BDDMockito.any(DomainEvent.class)))
            .thenReturn(Mono.just(productDeleted));

    Mono<List<DomainEvent>> listEvents = useCase.applyCommand(deleteProductCom)
            .collectList();

    StepVerifier
            .create(listEvents)
            .assertNext(events -> {
              ProductDeleted event = (ProductDeleted) events.get(0);

              assertEquals(1, events.size());
              assertEquals(PRODUCT_ID, event.getProductID());
            })
            .expectComplete()
            .log()
            .verify();

    BDDMockito.verify(repository).findById(BDDMockito.anyString());
    BDDMockito.verify(repository).saveEvent(BDDMockito.any(DomainEvent.class));
    BDDMockito.verify(repository).saveView(BDDMockito.any(Inventory.class));
  }

}