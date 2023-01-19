package co.sofka.challenge_jr.business.usecases;

import co.sofka.challenge_jr.application.repositories.MongoRepository;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import co.sofka.challenge_jr.application.repositories.models.ProductView;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetProductsUseCase {
  private final MongoRepository repository;

  public GetProductsUseCase(MongoRepository repository) {
    this.repository = repository;
  }

  public Flux<ProductView> getAll(String inventoryID) {
    return repository.findInventoryById(inventoryID)
            .flatMapIterable(InventoryView::getProducts);
  }
}
