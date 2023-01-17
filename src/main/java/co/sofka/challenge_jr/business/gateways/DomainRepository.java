package co.sofka.challenge_jr.business.gateways;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import co.sofka.challenge_jr.domain.Inventory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DomainRepository {
  Flux<DomainEvent> findById(String id);
  Mono<DomainEvent> saveEvent(DomainEvent event);
  Mono<InventoryView> saveView(Inventory inventory);
  Mono<InventoryView> findInventoryById(String id);
}
