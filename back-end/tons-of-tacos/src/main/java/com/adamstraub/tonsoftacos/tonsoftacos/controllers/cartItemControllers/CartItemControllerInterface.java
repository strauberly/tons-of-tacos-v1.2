package com.adamstraub.tonsoftacos.tonsoftacos.controllers.cartItemControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.cartItemsDto.GetCartItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.cartItemsDto.CartItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.CartItem;
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
@OpenAPIDefinition(info = @Info(title = "services pertaining to cart functions and building an order"),
        servers = {@Server(url="http://localhost:8080", description = "Local server")})
public interface CartItemControllerInterface {


    @Operation(
            summary = "Item added to cart is transferred to database.",
            description = "Having the items recorded will allow for persistence and allow for future features such as" +
                    "inventory tracking, trend tracking and sales analysis.",
            responses = {

                    @ApiResponse(
                            responseCode = "201",
                            description = "An order-id is created.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartItem.class))),
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

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add-to-cart")
    String addToCart(
            @RequestBody
            CartItemDto cartItemDto) throws InvalidPropertiesFormatException;


    //
    @Operation(
            summary = " Get all items stored to cart by its id.",
            description = "Provides functionality for contents of cart to be viewed.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order-items are returned.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartItem.class))),
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

    @GetMapping("/get-cart/{cartUuid}")
    @ResponseStatus(code = HttpStatus.OK)
    List<GetCartItemDto> findByCartUuid(
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
                                    schema = @Schema(implementation = CartItem.class))),
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
    CartItemDto updateCart(
            @PathVariable
            Integer orderItemId,
            @PathVariable
            Integer newQuantity);

    @Operation(
            summary = " Removes a cart id.",
            description = "Updates the cart by removing an id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Item removed from cart.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CartItem.class))),
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


