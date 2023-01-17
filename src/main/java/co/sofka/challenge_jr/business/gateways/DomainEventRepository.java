package co.sofka.challenge_jr.business.gateways;

import co.com.sofka.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainEventRepository {
  Flux<DomainEvent> findById(String id);
  Mono<DomainEvent> saveEvent(DomainEvent event);
}
