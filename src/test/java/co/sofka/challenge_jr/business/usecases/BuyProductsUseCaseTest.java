package co.sofka.challenge_jr.business.usecases;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.application.repositories.models.ProductsBuyView;
import co.sofka.challenge_jr.business.gateways.DomainRepository;
import co.sofka.challenge_jr.domain.Inventory;
import co.sofka.challenge_jr.domain.commands.BuyProducts;
import co.sofka.challenge_jr.domain.events.InventoryCreated;
import co.sofka.challenge_jr.domain.events.ProductAdded;
import co.sofka.challenge_jr.domain.events.ProductsBought;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BuyProductsUseCaseTest {

  public static final String ID_INVENTORY = "1";
  public static final String PRODUCT_ID = "50";
  @Mock
  private DomainRepository repository;

  @InjectMocks
  private BuyProductsUseCase useCase;

  @Test
  void buyProductsUseCase() {
    ArrayList<ProductsBuyView> productsToBuy = new ArrayList<>();
    productsToBuy.add(new ProductsBuyView(PRODUCT_ID, 20));
    BuyProducts buyProductsCom = new BuyProducts(ID_INVENTORY, productsToBuy, "David", "CC", "1000293315");

    InventoryCreated inventoryCreated = new InventoryCreated("sofka");
    ProductAdded productAddedPC = new ProductAdded("PC", 500, true, 8, 200);
    ProductsBought productsBought = new ProductsBought(
            productsToBuy,
            buyProductsCom.getClientName(),
            buyProductsCom.getIdType(),
            buyProductsCom.getIdClient()
    );


    BDDMockito.when(repository.findById(ID_INVENTORY))
            .thenReturn(Flux.just(inventoryCreated, productAddedPC));

    BDDMockito.when(repository.saveView(BDDMockito.any(Inventory.class)))
            .thenReturn(Mono.empty());

    BDDMockito.when(repository.saveEvent(BDDMockito.any(DomainEvent.class)))
            .thenReturn(Mono.just(productsBought));

    Mono<List<DomainEvent>> listEvents = useCase.applyCommand(buyProductsCom).collectList();

    StepVerifier
            .create(listEvents)
            .assertNext(events -> {
              ProductsBought event = (ProductsBought) events.get(0);

              assertEquals(1, events.size());
              assertEquals(productsBought.getBuyID(), event.getBuyID());
              assertEquals(1, event.getProductsBuy().size());
            })
            .expectComplete()
            .log()
            .verify();


    BDDMockito.verify(repository).findById(BDDMockito.anyString());
    BDDMockito.verify(repository).saveEvent(BDDMockito.any(DomainEvent.class));
    BDDMockito.verify(repository).saveView(BDDMockito.any(Inventory.class));
  }

}