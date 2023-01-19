package co.sofka.challenge_jr.application.routers;

import co.sofka.challenge_jr.application.repositories.models.BuyView;
import co.sofka.challenge_jr.business.usecases.*;
import co.sofka.challenge_jr.domain.commands.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
  @RouterOperation(
    path = "/inventory",
    method = RequestMethod.POST,
    produces = { MediaType.APPLICATION_JSON_VALUE },
    beanClass = CreateInventoryUseCase.class,
    beanMethod =  "applyCommand",
    operation = @Operation(
      operationId = "createInventory",
      tags = "Create Inventory",
      responses = {
        @ApiResponse(
          responseCode = "200",
          description = "Success",
          content = @Content(
            schema = @Schema(
                    implementation = CreateInventory.class
            )
          )
        ),
        @ApiResponse(
          responseCode = "400",
          description = "Wrong Arguments",
          content = @Content(
            schema = @Schema(
              implementation = Void.class
            )
          )
        )
      },
      requestBody = @RequestBody(
        content = @Content(
          schema = @Schema(
            implementation = CreateInventory.class
          )
        )
      )
    )
  )
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
  @RouterOperation(
    path = PRODUCT_URL,
    method = RequestMethod.POST,
    produces = { MediaType.APPLICATION_JSON_VALUE },
    beanClass = AddProductUseCase.class,
    beanMethod =  "applyCommand",
    operation = @Operation(
      operationId = "addProduct",
      tags = "Modify Inventory",
      responses = {
        @ApiResponse(
          responseCode = "200",
          description = "Success",
          content = @Content(
            schema = @Schema(
              implementation = AddProduct.class
            )
          )
        ),
        @ApiResponse(
          responseCode = "400",
          description = "Wrong Arguments",
          content = @Content(
            schema = @Schema(
              implementation = Void.class
            )
          )
        )
      },
      requestBody = @RequestBody(
        content = @Content(
          schema = @Schema(
            implementation = AddProduct.class
          )
        )
      )
    )
  )
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
  @RouterOperation(
    path = PRODUCT_URL,
    method = RequestMethod.DELETE,
    produces = { MediaType.APPLICATION_JSON_VALUE },
    beanClass = DeleteProductUseCase.class,
    beanMethod =  "applyCommand",
    operation = @Operation(
      operationId = "deleteProduct",
      tags = "Modify Inventory",
      responses = {
        @ApiResponse(
          responseCode = "200",
          description = "Success",
          content = @Content(
            schema = @Schema(
              implementation = DeleteProduct.class
            )
          )
        ),
        @ApiResponse(
          responseCode = "400",
          description = "Wrong Arguments",
          content = @Content(
            schema = @Schema(
              implementation = Void.class
            )
          )
        )
      },
      requestBody = @RequestBody(
        content = @Content(
          schema = @Schema(
            implementation = DeleteProduct.class
          )
        )
      )
    )
  )
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
  @RouterOperation(
    path = PRODUCT_URL,
    method = RequestMethod.PUT,
    produces = { MediaType.APPLICATION_JSON_VALUE },
    beanClass = UpdateProductUseCase.class,
    beanMethod =  "applyCommand",
    operation = @Operation(
      operationId = "updateProduct",
      tags = "Modify Inventory",
      responses = {
        @ApiResponse(
          responseCode = "200",
          description = "Success"
        ),
        @ApiResponse(
          responseCode = "400",
          description = "Wrong Arguments",
          content = @Content(
            schema = @Schema(
              implementation = Void.class
            )
          )
        )
      },
      requestBody = @RequestBody(
        content = @Content(
          schema = @Schema(
            implementation = UpdateProduct.class
          )
        )
      )
    )
  )
  public RouterFunction<ServerResponse> updateProduct(UpdateProductUseCase useCase) {
    return route(
            PUT(PRODUCT_URL).and(accept(MediaType.APPLICATION_JSON)),
            request ->
              request.bodyToMono(UpdateProduct.class)
              .flatMap(updateProduct ->
                  useCase.applyCommand(updateProduct)
                  .collectList()
                  .flatMap(events ->
                          ServerResponse.ok().build()
                  ))
              .onErrorResume(throwable -> ServerResponse.badRequest().build())
    );
  }

  @Bean
  @RouterOperation(
    path = "/buy",
    method = RequestMethod.POST,
    produces = { MediaType.APPLICATION_JSON_VALUE },
    beanClass = BuyProductsUseCase.class,
    beanMethod =  "applyCommand",
    operation = @Operation(
      operationId = "buyProducts",
      tags = "Modify Inventory",
      responses = {
        @ApiResponse(
          responseCode = "200",
          description = "Success",
          content = @Content(
            schema = @Schema(
              implementation = BuyProducts.class
            )
          )
        ),
        @ApiResponse(
          responseCode = "400",
          description = "Wrong Arguments",
          content = @Content(
            schema = @Schema(
              implementation = Void.class
            )
          )
        )
      },
      requestBody = @RequestBody(
        content = @Content(
          schema = @Schema(
            implementation = BuyProducts.class
          )
        )
      )
    )
  )
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
//             .onErrorResume(throwable -> ServerResponse.badRequest().build())
             )
    );
  }
}
