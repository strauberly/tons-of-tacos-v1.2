package com.adamstraub.tonsoftacos.tonsoftacos.controllers.orderItemControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.OrderItemDto;
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

//probably remap from orders to owners
@Validated
@RequestMapping(
  value = "api/orders/edit-order")
@OpenAPIDefinition(info = @Info(title = "services pertaining to cart functions and building an order"),
        servers = {@Server(url="http://localhost:8080", description = "Local server")})
public interface OrderItemControllerInterface {

//adds to an order' - rename method
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

    @PatchMapping("/add-to-order/{orderId}/{menuItemId}/{quantity}")
    String addToCart(
            @PathVariable
            Integer orderId, @PathVariable Integer menuItemId, @PathVariable Integer quantity) throws InvalidPropertiesFormatException;


//update order item
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

    @PatchMapping("/change-quantity/{orderId}/{orderItemId}/{newQuantity}")
    String updateItemQuantity(
            @PathVariable
            Integer orderId,
            @PathVariable
            Integer orderItemId,
            @PathVariable
            Integer newQuantity);

}



