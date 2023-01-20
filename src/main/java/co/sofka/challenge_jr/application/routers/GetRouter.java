package co.sofka.challenge_jr.application.routers;

import co.sofka.challenge_jr.application.repositories.models.BuyView;
import co.sofka.challenge_jr.application.repositories.models.ProductView;
import co.sofka.challenge_jr.business.usecases.GetBuysUseCase;
import co.sofka.challenge_jr.business.usecases.GetProductsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class GetRouter {
  @Bean
  @RouterOperation(
    path = "/products",
    method = RequestMethod.GET,
    produces = { MediaType.APPLICATION_JSON_VALUE },
    beanClass = GetProductsUseCase.class,
    beanMethod =  "getAll",
    operation = @Operation(
      operationId = "getAllProducts",
      tags = "Get from Inventory",
      responses = {
        @ApiResponse(
          responseCode = "200",
          description = "Success",
          content = @Content(
            schema = @Schema(
                implementation = ProductView.class
              )
          )
        ),
        @ApiResponse(
          responseCode = "204",
          description = "No products"
        )
      }
    )
  )
  public RouterFunction<ServerResponse> getProducts(GetProductsUseCase useCase) {
    return route(
            GET("/products"),
            request ->
              Mono.just(request.queryParam("inventoryID"))
                  .map(Optional::orElseThrow)
                  .flatMap(id ->
                    useCase.getAll(id)
                    .switchIfEmpty(Mono.error(new Throwable()))
                    .collectList()
                    .flatMap(inventoryViews ->
                            ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(inventoryViews)))
                  )
                .onErrorResume(throwable -> ServerResponse.noContent().build())
    );
  }

  @Bean
  @RouterOperation(
    path = "/buys",
    method = RequestMethod.GET,
    produces = { MediaType.APPLICATION_JSON_VALUE },
    beanClass = GetBuysUseCase.class,
    beanMethod =  "getAll",
    operation = @Operation(
      operationId = "getAllProducts",
      tags = "Get from Inventory",
      responses = {
        @ApiResponse(
          responseCode = "200",
          description = "Success",
          content = @Content(
            schema = @Schema(
              implementation = BuyView.class
            )
          )
        ),
        @ApiResponse(
          responseCode = "204",
          description = "No buys"
        )
      }
    )
  )
  public RouterFunction<ServerResponse> getBuys(GetBuysUseCase useCase) {
    return route(
            GET("/buys"),
            request ->
              Mono.just(request.queryParam("inventoryID"))
                .map(Optional::orElseThrow)
                .flatMap(id ->
                  useCase.getAll(id)
                  .switchIfEmpty(Mono.error(new Throwable()))
                  .collectList()
                  .flatMap(inventoryViews ->
                          ServerResponse.ok()
                          .contentType(MediaType.APPLICATION_JSON)
                          .body(BodyInserters.fromValue(inventoryViews)))
                )
                .onErrorResume(throwable -> ServerResponse.noContent().build())
    );
  }
}
