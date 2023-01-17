package co.sofka.challenge_jr.application.routers;

import co.sofka.challenge_jr.business.usecases.*;
import co.sofka.challenge_jr.domain.commands.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

@Configuration
public class CommandRouter {

  public static final String PRODUCT_URL = "/product";

  @Bean
  public RouterFunction<ServerResponse> createInventory(CreateInventoryUseCase useCase) {
    return
     route(
       POST("/inventory").and(accept(MediaType.APPLICATION_JSON)),
       request ->
         request.bodyToMono(CreateInventory.class)
           .flatMap(createInventory ->
              useCase.applyCommand(createInventory)
              .collectList()
              .flatMap(events ->
                      ServerResponse.created(URI.create("/inventory"))
                      .contentType(MediaType.APPLICATION_JSON)
                      .body(BodyInserters.fromValue(createInventory))
              ))
           .onErrorResume(throwable -> ServerResponse.badRequest().build())
     );
  }

  @Bean
  public RouterFunction<ServerResponse> addProduct(AddProductUseCase useCase) {
    return route(
            POST(PRODUCT_URL).and(accept(MediaType.APPLICATION_JSON)),
            request ->
              request.bodyToMono(AddProduct.class)
                .flatMap(addProduct ->
                  useCase.applyCommand(addProduct)
                    .collectList()
                    .flatMap(events ->
                            ServerResponse.created(URI.create("/inventory/product"))
                              .contentType(MediaType.APPLICATION_JSON)
                              .body(BodyInserters.fromValue(addProduct))
                    ))
                .onErrorResume(throwable -> ServerResponse.badRequest().build())
    );
  }

  @Bean
  public RouterFunction<ServerResponse> deleteProduct(DeleteProductUseCase useCase) {
    return route(
            DELETE(PRODUCT_URL).and(accept(MediaType.APPLICATION_JSON)),
            request ->
              request.bodyToMono(DeleteProduct.class)
                .flatMap(deletePro ->
                  useCase.applyCommand(deletePro)
                  .collectList()
                  .flatMap(events ->
                          ServerResponse.ok()
                          .contentType(MediaType.APPLICATION_JSON)
                          .body(BodyInserters.fromValue(deletePro))
                  ))
                .onErrorResume(throwable -> ServerResponse.badRequest().build())
    );
  }

  @Bean
  public RouterFunction<ServerResponse> updateProduct(UpdateProductUseCase useCase) {
    return route(
            PUT(PRODUCT_URL).and(accept(MediaType.APPLICATION_JSON)),
            request ->
              request.bodyToMono(UpdateProduct.class)
              .flatMap(updateProduct ->
                  useCase.applyCommand(updateProduct)
                  .collectList()
                  .flatMap(events ->
                          ServerResponse.ok()
                          .contentType(MediaType.APPLICATION_JSON)
                          .body(BodyInserters.fromValue(updateProduct)
                  ))
              .onErrorResume(throwable -> ServerResponse.badRequest().build()))
    );
  }

  @Bean
  public RouterFunction<ServerResponse> buyProducts(BuyProductsUseCase useCase) {
    return route(
            POST("/buy").and(accept(MediaType.APPLICATION_JSON)),
            request ->
             request.bodyToMono(BuyProducts.class)
             .flatMap(buyProducts ->
                     useCase.applyCommand(buyProducts)
                     .collectList()
                     .flatMap(events ->
                        ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(buyProducts)
                     ))
             .onErrorResume(throwable -> ServerResponse.badRequest().build()))
    );
  }
}
