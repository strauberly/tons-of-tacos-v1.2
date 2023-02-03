package com.adamstraub.tonsoftacos.tonsoftacos.controller;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Validated
@RestController
@RequestMapping("/api/order")
@OpenAPIDefinition(info = @Info(title = "Order Item Service"),
        servers = {@Server(url="http://localhost:8080/", description = "Local server")})
public class BaseOrderItemController {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Operation(
            summary = "Order item added to cart is transferred to database",
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
                            responseCode = "404",
                            description = "No menu-item found according to input.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

            }
    )

    @PostMapping("/add-to-cart")
    public ResponseEntity<OrderItem> addToCart(@RequestBody OrderItem orderItem){
        orderItemRepository.save(orderItem);
        return new ResponseEntity<>(orderItem, HttpStatus.CREATED);
    }
}
