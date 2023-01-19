package co.sofka.challenge_jr.business.usecases;

import co.sofka.challenge_jr.application.repositories.ViewRepository;
import co.sofka.challenge_jr.application.repositories.models.BuyView;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetBuysUseCase {
  private final ViewRepository repository;

  public GetBuysUseCase(ViewRepository repository) {
    this.repository = repository;
  }

  public Flux<BuyView> getAll(String id) {
    return repository.findInventoryById(id)
            .flatMapIterable(InventoryView::getBuys);
  }
}
