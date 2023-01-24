package co.sofka.challenge_jr.application.routers;

import co.sofka.challenge_jr.application.repositories.models.BuyView;
import co.sofka.challenge_jr.application.repositories.models.ProductView;
import co.sofka.challenge_jr.application.repositories.models.ProductsBuyView;
import co.sofka.challenge_jr.business.usecases.GetBuysUseCase;
import co.sofka.challenge_jr.business.usecases.GetProductsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {GetRouter.class, GetProductsUseCase.class})
@WebFluxTest
class GetRouterTest {

  @Autowired
  private ApplicationContext context;

  @MockBean
  private GetProductsUseCase getProductsUseCase;

  @MockBean
  private GetBuysUseCase getBuysUseCase;
  private WebTestClient webTestClient;

  @BeforeEach
  public void setUp(){
    webTestClient = WebTestClient.bindToApplicationContext(context).build();
  }

  @Test
  void getProducts() {
    Set<ProductView> productViews = createProducts();
    BDDMockito.when(getProductsUseCase.getAll("1")).thenReturn(Flux.fromIterable(productViews));


    webTestClient
          .get()
          .uri(uriBuilder ->
            uriBuilder
              .path("/products")
              .queryParam("idInventory", "1")
              .build()
          )
          .exchange()
          .expectStatus().isOk()
          .expectBody(Set.class)
          .value(response -> {
            assertEquals(4, response.size());
          });

    webTestClient
            .get()
            .uri(uriBuilder ->
                    uriBuilder
                    .path("/products")
                    .queryParam("idInventory", "2")
                    .build()
            )
            .exchange()
            .expectStatus().isNoContent();

    BDDMockito.verify(getProductsUseCase, BDDMockito.times(2)).getAll(BDDMockito.anyString());
  }


  @Test
  void getBuys() {

    List<BuyView> buyViews = createBuys();
    BDDMockito.when(getBuysUseCase.getAll("1")).thenReturn(Flux.fromIterable(buyViews));

    webTestClient
            .get()
            .uri(uriBuilder ->
                    uriBuilder
                    .path("/buys")
                    .queryParam("idInventory", "1")
                    .build()
            )
            .exchange()
            .expectStatus().isOk()
            .expectBody(Set.class)
            .value(response -> {
              assertEquals(2, response.size());
            });;

    webTestClient
            .get()
            .uri(uriBuilder ->
                    uriBuilder
                    .path("/buys")
                    .queryParam("idInventory", "2")
                    .build()
            )
            .exchange()
            .expectStatus().isNoContent();

    BDDMockito.verify(getBuysUseCase, BDDMockito.times(2)).getAll(BDDMockito.anyString());
  }

  private Set<ProductView> createProducts() {
    ProductView pc = new ProductView("1", "PC", 500, true, 8, 2000);
    ProductView book = new ProductView("2", "Book", 50, true, 1, 10);
    ProductView table = new ProductView("3", "Table", 20, true, 1, 5);
    ProductView monitor = new ProductView("4", "Monitor", 0, false, 1, 2);
    return new HashSet<>(Arrays.asList(pc, book, table, monitor));
  }

  private List<BuyView> createBuys(){
    List<ProductsBuyView> productsDavid = Arrays.asList(
            new ProductsBuyView("1", 20),
            new ProductsBuyView("2", 50)
    );
    BuyView davidBuy = new BuyView(UUID.randomUUID().toString(), Date.from(Instant.now()), "CC", "1000293315", "David", productsDavid);

    List<ProductsBuyView> productsIsa = Arrays.asList(
            new ProductsBuyView("1", 10),
            new ProductsBuyView("2", 60),
            new ProductsBuyView("3", 100)
    );
    BuyView isaBuy = new BuyView(UUID.randomUUID().toString(), Date.from(Instant.now()), "CC", "1025891626", "Isa", productsIsa);
    return Arrays.asList(davidBuy, isaBuy);
  }

}