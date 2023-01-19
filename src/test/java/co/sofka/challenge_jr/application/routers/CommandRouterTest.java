package co.sofka.challenge_jr.application.routers;

import co.sofka.challenge_jr.business.usecases.*;
import co.sofka.challenge_jr.domain.commands.*;
import co.sofka.challenge_jr.domain.events.InventoryCreated;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {
        CommandRouter.class,
        CreateInventoryUseCase.class,
        AddProductUseCase.class,
        DeleteProductUseCase.class,
        UpdateProductUseCase.class,
        BuyProductsUseCase.class
})
@WebFluxTest
class CommandRouterTest {
  @Autowired
  private ApplicationContext context;

  @MockBean
  private CreateInventoryUseCase createInventoryUseCase;

  @MockBean
  private AddProductUseCase addProductUseCase;

  @MockBean
  private DeleteProductUseCase deleteProductUseCase;

  @MockBean
  private UpdateProductUseCase updateProductUseCase;

  @MockBean
  private BuyProductsUseCase buyProductsUseCase;

  private WebTestClient webTestClient;

  @BeforeEach
  public void setUp(){
    webTestClient = WebTestClient.bindToApplicationContext(context).build();
  }

  @Test
  void createInventory() {
    CreateInventory createInventoryCom = new CreateInventory("1", "Sofka");

    BDDMockito.when(createInventoryUseCase.applyCommand(BDDMockito.any(CreateInventory.class)))
            .thenReturn(Flux.empty());

    webTestClient
        .post()
        .uri("/inventory")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .body(Mono.just(createInventoryCom), CreateInventory.class)
        .exchange()
        .expectStatus().isCreated();

    BDDMockito.verify(createInventoryUseCase).applyCommand(BDDMockito.any(CreateInventory.class));
  }

  @Test
  void addProduct() {
    AddProduct addProductCom = new AddProduct("1", "PC", 500, true, 8, 200);

    BDDMockito.when(addProductUseCase.applyCommand(BDDMockito.any(AddProduct.class)))
            .thenReturn(Flux.empty());

    webTestClient
            .post()
            .uri("/product")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(addProductCom), AddProduct.class)
            .exchange()
            .expectStatus().isCreated();

    BDDMockito.verify(addProductUseCase).applyCommand(BDDMockito.any(AddProduct.class));
  }

  @Test
  void updateProduct() {
    UpdateProduct updateProductCom = new UpdateProduct("1", "1", 500, "PC", 1, 10);

    BDDMockito.when(updateProductUseCase.applyCommand(BDDMockito.any(UpdateProduct.class)))
            .thenReturn(Flux.empty());

    webTestClient
            .put()
            .uri("/product")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(updateProductCom), UpdateProduct.class)
            .exchange()
            .expectStatus().isOk();

    BDDMockito.verify(updateProductUseCase).applyCommand(BDDMockito.any(UpdateProduct.class));
  }

  @Test
  void deleteProduct() {
    DeleteProduct deleteProductCom = new DeleteProduct("1", "1");

    BDDMockito.when(deleteProductUseCase.applyCommand(BDDMockito.any(DeleteProduct.class)))
            .thenReturn(Flux.empty());

    webTestClient
            .method(HttpMethod.DELETE)
            .uri("/product")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(deleteProductCom), DeleteProduct.class)
            .exchange()
            .expectStatus().isOk();

    BDDMockito.verify(deleteProductUseCase).applyCommand(BDDMockito.any(DeleteProduct.class));
  }

  @Test
  void buyProducts() {
    BuyProducts buyProductsCom = new BuyProducts("1", new HashSet<>(), "David", "CC", "892374815");

    BDDMockito.when(buyProductsUseCase.applyCommand(BDDMockito.any(BuyProducts.class)))
            .thenReturn(Flux.empty());

    webTestClient
            .post()
            .uri("/buy")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(buyProductsCom), BuyProducts.class)
            .exchange()
            .expectStatus().isOk();

    BDDMockito.verify(buyProductsUseCase).applyCommand(BDDMockito.any(BuyProducts.class));
  }
}