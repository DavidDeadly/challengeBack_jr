package co.sofka.challenge_jr.business.gateways;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.domain.generic.DomainEvent;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface CommandExecutor<C extends Command> {
  Flux<DomainEvent> applyCommand(C command);
}
