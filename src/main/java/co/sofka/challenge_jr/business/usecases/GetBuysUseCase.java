package co.sofka.challenge_jr.business.usecases;

import co.sofka.challenge_jr.application.repositories.MongoRepository;
import co.sofka.challenge_jr.application.repositories.models.BuyView;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Service
public class GetBuysUseCase {
  private final MongoRepository repository;

  public GetBuysUseCase(MongoRepository repository) {
    this.repository = repository;
  }

  public Flux<BuyView> getAll(Optional<String> id) {
    if(id.isEmpty()) return Flux.empty();
    return repository.findInventoryById(id.get())
            .flatMapIterable(InventoryView::getBuys);
  }
}