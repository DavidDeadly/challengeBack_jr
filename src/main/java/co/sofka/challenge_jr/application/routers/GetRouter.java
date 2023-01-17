package co.sofka.challenge_jr.application.routers;

import co.sofka.challenge_jr.business.usecases.GetProductsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetRouter {
  @Bean
  public RouterFunction<ServerResponse> getProducts(GetProductsUseCase useCase) {
    return route(
            GET("/products"),
            request ->
              useCase.getAll(request.queryParam("id"))
                .switchIfEmpty(Mono.error(new Throwable()))
                .collectList()
                .flatMap(inventoryViews ->
                  ServerResponse.ok()
                  .contentType(MediaType.APPLICATION_JSON)
                  .body(BodyInserters.fromValue(inventoryViews)))
                .onErrorResume(throwable -> ServerResponse.noContent().build())
    );
  }
}
