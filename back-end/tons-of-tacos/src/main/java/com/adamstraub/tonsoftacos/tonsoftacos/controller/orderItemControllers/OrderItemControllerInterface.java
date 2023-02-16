package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;//package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;
//
//import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.servers.Server;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RequestMapping(
  value = "api/order")
@OpenAPIDefinition(info = @Info(title = "services pertaining to cart functions"),
        servers = {@Server(url="http://localhost:8080", description = "Local server")})
public interface OrderItemControllerInterface {
//
    @Operation(
            summary = " Get all items stored to cart by its id.",
            description = "Provides functionality for contents of cart to be viewed.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order-items are returned.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No order-items found according to input.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

            }
    )
    @GetMapping("/get-cart/{orderUuid}")
    @ResponseStatus(code = HttpStatus.OK)
    List<OrderItem> findByOrderUuid(
            @RequestParam
            String orderUuid);

    @Operation(
            summary = " updates an order item quantity.",
            description = "Will update the quantity adn a new total based on the new quantity.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item removed.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No order-items found according to input.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

            }
    )
@PatchMapping("/update-cart/{orderItemId}/{newQuantity}")
    OrderItem updateCart(
            @PathVariable
            Integer orderItemId,
            @PathVariable
            Integer newQuantity);

    @Operation(
            summary = " Removes a cart item.",
            description = "Updates the cart by removing an item",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item removed from cart.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No order-items found according to input.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

            }
    )
    @DeleteMapping("/remove-cart-item/{orderItemId}")
    void removeCartItem(@PathVariable Integer orderItemId);

    @Operation(
            summary = "Item added to cart is transferred to database.",
            description = "Having the items recorded will allow for persistence and allow for future features such as" +
                    "inventory tracking, trend tracking and sales analysis.",
            responses = {

                    @ApiResponse(
                            responseCode = "201",
                            description = "An order-item is created.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

//                    parameters = {
//            @Parameter(name = "order/id", allowEmptyValue = false, required = false,
//                    description = "Corresponds to menu item desired (i.e, api/menu-item/1  <- will return " +
//                            "menu-item " +
//                            "limon). Additionally api/menu-item/id?id=1 will return the same value. " ),

            }
    )
    @PostMapping("/add-to-cart")
    @ResponseStatus(code = HttpStatus.CREATED)
    OrderItem addToCart(
            @RequestBody
            OrderItem orderItem ) throws JsonProcessingException;
}

