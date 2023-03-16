package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Validated
//@RequestMapping("api/menu")
//@OpenAPIDefinition(info = @Info(title = "Services for Tons of Tacos."),
//        servers = {@Server(url="http://localhost:8080/", description = "Local server")})
//public interface MenuItemControllerInterface {
//    @Operation(
//            summary = "Return menu id by id.",
//            description = "Return specific menu items by id for use in building a cart and and creating an order",
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "A menu-id is returned.",
//                            content = @Content(mediaType = "application/json",
//                                    schema = @Schema(implementation = MenuItem.class))),
//                    @ApiResponse(
//                            responseCode = "400",
//                            description = "Request parameters invalid.",
//                            content = @Content(mediaType = "application/json")),
//                    @ApiResponse(
//                            responseCode = "404",
//                            description = "No menu-id found according to input.",
//                            content = @Content(mediaType = "application/json")),
//                    @ApiResponse(
//                            responseCode = "500",
//                            description = "An unplanned error occured.",
//                            content = @Content(mediaType = "application/json")),
//
//            },
//            parameters = {
//                    @Parameter(name = "/id", allowEmptyValue = false, required = false,
//                            description = "api/menu-id/id?id={idNumber} returns a menu id by id number."),
//            }
//    )
//
//    @GetMapping("/id")
//    @ResponseStatus(code = HttpStatus.OK)
//    Optional<Optional<MenuItem>> getById(
//            @RequestParam(required = true)
//            String id);

@Validated
@RequestMapping(
        value = "api/owners-tools")
@OpenAPIDefinition(info = @Info(title = "services pertaining to checkout functions and monitoring orders"),
        servers = {@Server(url="http://localhost:8080", description = "Local server")})
public interface OwnersControllerInterface {
    // for all if data returned it shows id for ease of selection
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
    @Transactional
    @GetMapping("/get-orders")
    List<OwnersGetOrderDto> getAllOrders();

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
    Orders getOrderByUid(@RequestParam String orderUid);


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
    @GetMapping("/get-order/customer")
   Orders getOrderByCustomer(@RequestParam String customer);


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
    String todaysSales();

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
