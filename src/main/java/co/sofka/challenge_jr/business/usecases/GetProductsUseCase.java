package co.sofka.challenge_jr.business.usecases;

import co.sofka.challenge_jr.application.repositories.MongoRepository;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import co.sofka.challenge_jr.application.repositories.models.ProductView;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
public class GetProductsUseCase {
  private final MongoRepository repository;

  public GetProductsUseCase(MongoRepository repository) {
    this.repository = repository;
  }

  public Flux<ProductView> getAll(Optional<String> id) {
    if(id.isEmpty()) return Flux.empty();
    return repository.findInventoryById(id.get())
            .flatMapIterable(InventoryView::getProducts);
  }
}
