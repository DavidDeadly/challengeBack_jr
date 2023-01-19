package co.sofka.challenge_jr.business.usecases;

import co.sofka.challenge_jr.application.repositories.ViewRepository;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import co.sofka.challenge_jr.application.repositories.models.ProductView;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetProductsUseCase {
  private final ViewRepository repository;

  public GetProductsUseCase(ViewRepository repository) {
    this.repository = repository;
  }

  public Flux<ProductView> getAll(String id) {
    return repository.findInventoryById(id)
            .flatMapIterable(InventoryView::getProducts);
  }
}
