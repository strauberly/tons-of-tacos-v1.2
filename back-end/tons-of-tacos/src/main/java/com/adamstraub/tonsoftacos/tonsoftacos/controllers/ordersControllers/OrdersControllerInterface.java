package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ordersControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.NewOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.ReturnOrderToCustomerDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@Validated
@RequestMapping(
        value = "api/order")
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
    ReturnOrderToCustomerDto createOrder(@RequestBody NewOrderDto order) throws Exception;
}