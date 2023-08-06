package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers.orders;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.DailySales;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.OrderReturnedToOwner;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;




@Validated
@RequestMapping(
        value = "api/owners-tools/orders")
@OpenAPIDefinition(info = @Info(title = "services pertaining to functions reserved for the owners of tons of tacos."),
        servers = {@Server(url="http://localhost:8080", description = "Local server")})
public interface OwnersOrdersControllerInterface {

//    get all orders
    @Operation(
            summary = "All orders returned.",
            description = """
                  This endpoint will return all orders. For owner use only at this time.""",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All orders returned.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No orders found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @Transactional
    @GetMapping("/get-orders")
    List<OrderReturnedToOwner> getAllOrders();

//    get an order by order id
    @Operation(
            summary = "An order is returned by id.",
            description = """
                  For owner use only at this time.""",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order is returned.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No orders found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @Transactional
    @GetMapping("/get-order/orderId")
    OrderReturnedToOwner getOrderById(@RequestParam Integer orderId);

//    get an order by uid
    @Operation(
            summary = "An order is returned by uid.",
            description = """
                  For owner use only at this time.""",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order is returned.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No orders found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @Transactional
    @GetMapping("/get-order/orderUid")
    OrderReturnedToOwner getOrderByUid(@RequestParam String orderUid);

//    get an order by customer name
    @Operation(
            summary = "An order is returned by customer name.",
            description = """
                  For owner use only at this time.""",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order is returned.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No orders found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @Transactional
    @GetMapping("/get-order/customer")
   List <OrderReturnedToOwner> getOpenOrderByCustomer(@RequestParam String customer);

// mark food ready by id
    @Operation(
            summary = "Marks an order as having food ready for pick up.",
            description = "Implemented for ease of use.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order ready.",
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
    @Transactional
    @PutMapping("/order-ready/{orderId}")
    OrderReturnedToOwner orderReady(@PathVariable Integer orderId);


    @Operation(
            summary = "Closes an order by id.",
            description = "Allows for marking an order as closed once food has been picked up and payment received.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order closed.",
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
    @Transactional
    @PutMapping("/close-order/{orderId}")
    OrderReturnedToOwner closeOrder(@PathVariable Integer orderId);


// delete order by id
    @Operation(
            summary = "Deletes an order by id.",
            description = "Implemented for ease of use.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order deleted.",
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
    @Transactional
    @DeleteMapping("/delete-order/{orderId}")
    String deleteOrder(@PathVariable Integer orderId);

//    add menu item to order
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
    @Transactional
    @PutMapping("/add-to-order/{orderId}/{menuItemId}/{quantity}")
    String addToOrder(
            @PathVariable
            Integer orderId, @PathVariable Integer menuItemId, @PathVariable Integer quantity);

//    edit order item
    @Operation(
            summary = " Updates the quantity of an item in an order and if quantity equals zero the item is removed " +
                    "from order.",
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
    @PutMapping("/update-order-item/{orderId}/{orderItemId}/{newQuantity}")
    String updateOrderItemQuantity(
            @PathVariable
            Integer orderId,
            @PathVariable
            Integer orderItemId,
            @PathVariable
            Integer newQuantity);

// get todays sales
    @Operation(
            summary = "Gets sales for today's closed orders.",
            description = "Will allow for owner to see what they have brought in through online orders for the day." +
                    "Future use cases in mind for helping owners to have more in depth sale tracking.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Order deleted.",
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
    @GetMapping("/sales")
    DailySales todaysSales();

}
