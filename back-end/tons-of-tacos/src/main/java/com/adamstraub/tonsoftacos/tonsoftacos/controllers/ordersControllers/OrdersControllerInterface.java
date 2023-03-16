package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ordersControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
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
        value = "api/orders")
@OpenAPIDefinition(info = @Info(title = "services pertaining to checkout functions and monitoring orders"),
        servers = {@Server(url="http://localhost:8080", description = "Local server")})
public interface OrdersControllerInterface {
//    order is written to database
    @Operation(
          summary = "A completed order is transferred to database.",
          description = """
                  After being written to database information pertaining to orders will be viewable to owners
                  of tons of taco only to start in order for them to manage address.""",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "An order is created.",
                            content = @Content(mediaType = "application/json")),
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
    @PostMapping("/checkout")
    void createOrder(@RequestBody Orders order);



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
//    @GetMapping("/get-orders")
//    List<Orders> getAllOrders();
    @GetMapping("/get-orders")
    List<GetOrdersDto> getAllOrders();

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
    @GetMapping("/get-order/orderUid")
            GetOrdersDto getOrderByUid(@RequestParam String orderUid);


//    //    get an order by customer name
//    @Operation(
//            summary = "An order is returned by customer name.",
//            description = """
//                  For owner use only at this time.""",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "Order is returned.",
//                            content = @Content(mediaType = "application/json")),
//                    @ApiResponse(
//                            responseCode = "400",
//                            description = "Request parameters invalid.",
//                            content = @Content(mediaType = "application/json")),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "No orders found.",
//                            content = @Content(mediaType = "application/json")),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "An unplanned error occured.",
//                            content = @Content(mediaType = "application/json")),
//            }
//    )
//    @GetMapping("/get-order/customer")
//    GetOrdersDto getOrderByCustomer(@RequestParam String customer);

//    //    edit order item.
//        @Operation(
//                summary = "Edit an item in the order.",
//                description = """
//                      For owner use only at this time. To be used for correcting order errors.""",
//                responses = {
//                        @ApiResponse(
//                                responseCode = "200",
//                                description = "Order is returned.",
//                                content = @Content(mediaType = "application/json")),
//                        @ApiResponse(
//                                responseCode = "400",
//                                description = "Request parameters invalid.",
//                                content = @Content(mediaType = "application/json")),
//                        @ApiResponse(
//                                responseCode = "404",
//                                description = "No orders found.",
//                                content = @Content(mediaType = "application/json")),
//                        @ApiResponse(
//                                responseCode = "500",
//                                description = "An unplanned error occured.",
//                                content = @Content(mediaType = "application/json")),
//                }
//        )
//        @PatchMapping("/update-order/{orderUid}/{orderItemId}/{quantity}")
//        void updateOrderByUid(@PathVariable String orderUid, Integer orderItemId);
//
//    // get todays sales
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
    String todaysSales();


//    // close order by id
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
    @PatchMapping("/close-order/{orderId}")
    void closeOrder(@PathVariable Integer orderId);


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
    @PatchMapping("/food-ready/{orderId}")
    void foodReady(@PathVariable Integer orderId);




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
    @DeleteMapping("/delete-order/{orderId}")
    void deleteOrder(@PathVariable Integer orderId);
}