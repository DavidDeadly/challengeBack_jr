package co.sofka.challenge_jr.application.repositories;

import co.com.sofka.domain.generic.DomainEvent;
import co.sofka.challenge_jr.application.repositories.models.Event;
import co.sofka.challenge_jr.application.repositories.models.InventoryView;
import co.sofka.challenge_jr.application.repositories.models.ProductView;
import co.sofka.challenge_jr.business.gateways.DomainRepository;
import co.sofka.challenge_jr.domain.Inventory;
import com.google.gson.Gson;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepository implements DomainRepository {
  private final ReactiveMongoTemplate template;
  private final Gson gson = new Gson();

  public MongoRepository(ReactiveMongoTemplate template) {
    this.template = template;
  }

  @Override
  public Flux<DomainEvent> findById(String id) {
    return
      Mono.just(id)
      .map(aggId ->
        new Query(Criteria.where("aggregateId").is(aggId))
      )
      .flatMapMany(query -> template.find(query, Event.class))
      .map(event -> {
        try {
          return (DomainEvent) gson.fromJson(event.getEventBody(), Class.forName(event.getTypeName()));
        } catch(ClassNotFoundException err) {
          throw new IllegalArgumentException(err);
        }
      });
  }

  @Override
  public Mono<DomainEvent> saveEvent(DomainEvent domainEvent) {
    return
      Mono.just(domainEvent)
      .map(event -> new Event(
              event.aggregateRootId(),
              gson.toJson(event),
              event.getClass().getCanonicalName()
      ))
      .flatMap(template::save)
      .map(ev -> domainEvent);
  }

  @Override
  public Mono<InventoryView> saveView(Inventory inventory) {
    return Mono.just(inventory)
            .map(Inventory::toInventoryView)
            .flatMap(template::save);
  }

  @Override
  public Mono<InventoryView> findInventoryById(String id) {
    return Mono.just(id)
            .map(strId -> new Query(Criteria.where("id").is(strId)))
            .flatMap(query -> template.findOne(query, InventoryView.class));
  }
}
