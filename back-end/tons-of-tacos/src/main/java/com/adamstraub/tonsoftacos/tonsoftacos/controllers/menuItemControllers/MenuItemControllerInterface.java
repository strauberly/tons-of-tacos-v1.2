package com.adamstraub.tonsoftacos.tonsoftacos.controllers.menuItemControllers;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Validated
@RequestMapping("api/menu")
@OpenAPIDefinition(info = @Info(title = "Services for Tons of Tacos."),
        servers = {@Server(url="http://localhost:8080/", description = "Local server")})
public interface MenuItemControllerInterface {
    @Operation(
            summary = "Return menu id by id.",
            description = "Return specific menu items by id for use in building a cart and and creating an order",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A menu-id is returned.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MenuItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No menu-id found according to input.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

            },
            parameters = {
                    @Parameter(name = "/id", allowEmptyValue = false, required = false,
                            description = "api/menu-id/id?id={idNumber} returns a menu id by id number."),
            }
    )

    @GetMapping("/id")
    Optional<Optional<MenuItem>> getById(
            @RequestParam(required = true)
            String id);



    @Operation(
            summary = "Return menu items by category.",
            description = "Return specific menu items by category for use in building a cart and and creating an order",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Menu items returned by category.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MenuItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No menu-id found according to input.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

            },
            parameters = {
                    @Parameter(name = "category", allowEmptyValue = false, required = false,
                            description = "Calling menu items by category can be obtained through " +
                                    "api/menu-id/category?category= <your desired id category>."),
            }
    )

    @GetMapping("/category")
    List<MenuItem> getByCategory(
            @RequestParam(required = true)
            String category);
}

