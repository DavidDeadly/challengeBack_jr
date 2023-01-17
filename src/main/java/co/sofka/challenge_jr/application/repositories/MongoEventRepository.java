package co.sofka.challenge_jr.application.repositories;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.business.gateways.DomainEventRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoEventRepository implements DomainEventRepository {

  @Override
  public Flux<DomainEvent> findById(String id) {
    return null;
  }

  @Override
  public Mono<DomainEvent> saveEvent(DomainEvent event) {
    return null;
  }
}
