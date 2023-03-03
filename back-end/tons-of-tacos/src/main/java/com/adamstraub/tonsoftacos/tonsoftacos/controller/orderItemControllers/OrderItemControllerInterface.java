package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
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
import java.util.InvalidPropertiesFormatException;
import java.util.List;


@Validated
@RequestMapping(
  value = "api/order")
@OpenAPIDefinition(info = @Info(title = "services pertaining to cart functions"),
        servers = {@Server(url="http://localhost:8080", description = "Local server")})
public interface OrderItemControllerInterface {


    @Operation(
            summary = "Item added to cart is transferred to database.",
            description = "Having the items recorded will allow for persistence and allow for future features such as" +
                    "inventory tracking, trend tracking and sales analysis.",
            responses = {

                    @ApiResponse(
                            responseCode = "201",
                            description = "An order-id is created.",
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
            }
    )
//    entity
//    @ResponseStatus(code = HttpStatus.CREATED)
//    @PostMapping("/add-to-cart")
//    OrderItem addToCart(
//            @RequestBody
//            OrderItem orderItem) throws InvalidPropertiesFormatException;

//dto
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add-to-cart")
    String addToCart(
            @RequestBody
            OrderItemDto orderItemDto) throws InvalidPropertiesFormatException;


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
//    entity
//    @GetMapping("/get-cart/{cartUuid}")
//    @ResponseStatus(code = HttpStatus.OK)
//    List<OrderItem> findByOrderUuid(
//            @RequestParam
//            String cartUuid);

//
    @GetMapping("/get-cart/{cartUuid}")
    @ResponseStatus(code = HttpStatus.OK)
    List<GetOrderItemDto> findByCartUuid(
            @RequestParam
            String cartUuid);



    @Operation(
            summary = " Updates the quantity of an id in a cart and if quantity equals zero the id is deleted " +
                    "from cart.",
            description = "Updates the cart by removing an id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Quantity updated.",
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
//entity
//    @PatchMapping("/update-cart/{orderItemId}/{newQuantity}")
//    OrderItem updateCart(
//            @PathVariable
//            Integer orderItemId,
//            @PathVariable
//            Integer newQuantity);
// dto
    @PatchMapping("/update-cart/{orderItemId}/{newQuantity}")
    OrderItem updateCart(
            @PathVariable
            Integer orderItemId,
            @PathVariable
            Integer newQuantity);

    //
    @Operation(
            summary = " Removes a cart id.",
            description = "Updates the cart by removing an id",
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
}
//


