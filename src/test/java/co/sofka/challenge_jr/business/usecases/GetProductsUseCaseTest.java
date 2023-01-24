package co.sofka.challenge_jr.business.usecases;

import co.sofka.challenge_jr.application.repositories.MongoRepository;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import co.sofka.challenge_jr.application.repositories.models.ProductView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GetProductsUseCaseTest {
  public static final String INVENTORY_ID = "1";
  @Mock
  private MongoRepository repository;

  @InjectMocks
  private GetProductsUseCase useCase;

  @Test
  void getProductsUseCase() {
    List<ProductView> products = createProducts();

    InventoryView sofkaInventory = new InventoryView(INVENTORY_ID, "sofka");
    sofkaInventory.setProducts(products);

    BDDMockito.when(repository.findInventoryById(BDDMockito.anyString()))
            .thenReturn(Mono.just(sofkaInventory));

    Mono<List<ProductView>> allProducts = useCase.getAll(INVENTORY_ID)
            .collectList();

    StepVerifier
            .create(allProducts)
            .assertNext(productsView -> {
              assertEquals(4, productsView.size());
            })
            .expectComplete()
            .log()
            .verify();


    BDDMockito.verify(repository).findInventoryById(BDDMockito.anyString());
  }


  private List<ProductView> createProducts(){
    ProductView pc = new ProductView("1", "PC", 500, true, 8, 2000);
    ProductView book = new ProductView("2", "Book", 50, true, 1, 10);
    ProductView table = new ProductView("3", "Table", 20, true, 1, 5);
    ProductView monitor = new ProductView("4", "Monitor", 0, false, 1, 2);
    return new ArrayList<>(Arrays.asList(pc, book, table, monitor));
  }

}