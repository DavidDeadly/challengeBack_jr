package co.sofka.challenge_jr.business.usecases;

import co.sofka.challenge_jr.application.repositories.ViewRepository;
import co.sofka.challenge_jr.application.repositories.models.BuyView;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import co.sofka.challenge_jr.application.repositories.models.ProductsBuyView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class GetBuysUseCaseTest {
  @Mock
  private ViewRepository repository;

  @InjectMocks
  private  GetBuysUseCase useCase;
  private static final String INVENTORY_ID = "1";

  @Test
  void getBuysUseCase() {

    Set<BuyView> buys = createBuys();

    InventoryView sofkaInventory = new InventoryView(INVENTORY_ID, "sofka");
    sofkaInventory.setBuys(buys);

    BDDMockito.when(repository.findInventoryById(BDDMockito.anyString()))
            .thenReturn(Mono.just(sofkaInventory));

    Mono<List<BuyView>> allBuys = useCase.getAll(INVENTORY_ID)
            .collectList();

    StepVerifier
            .create(allBuys)
            .assertNext(buyViews -> {
              assertEquals(2, buyViews.size());

              BuyView davidBuy = getBuyByClientName(buyViews, "David");
              BuyView isaBuy = getBuyByClientName(buyViews, "Isa");

              assertEquals(3, isaBuy.getProducts().size());
              assertEquals(2, davidBuy.getProducts().size());
            })
            .expectComplete()
            .log()
            .verify();


    BDDMockito.verify(repository).findInventoryById(BDDMockito.anyString());
  }

  Set<BuyView> createBuys(){
    HashSet<ProductsBuyView> productsDavid = new HashSet<>(Arrays.asList(
            new ProductsBuyView("1", 20),
            new ProductsBuyView("2", 50)
    ));
    BuyView davidBuy = new BuyView(UUID.randomUUID().toString(), Date.from(Instant.now()), "CC", "1000293315", "David", productsDavid);

    HashSet<ProductsBuyView> productsIsa = new HashSet<>(Arrays.asList(
            new ProductsBuyView("1", 10),
            new ProductsBuyView("2", 60),
            new ProductsBuyView("3", 100)
    ));
    BuyView isaBuy = new BuyView(UUID.randomUUID().toString(), Date.from(Instant.now()), "CC", "1025891626", "Isa", productsIsa);
    return new HashSet<>(Arrays.asList(davidBuy, isaBuy));
  }

  BuyView getBuyByClientName(List<BuyView> buys, String name) {
    return buys.stream().filter(buyView ->
            buyView.getClientName().equals(name)
    ).findFirst().orElseThrow();
  }

}