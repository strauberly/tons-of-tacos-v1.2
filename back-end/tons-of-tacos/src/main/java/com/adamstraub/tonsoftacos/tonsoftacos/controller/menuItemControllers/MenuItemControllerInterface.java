package com.adamstraub.tonsoftacos.tonsoftacos.controller.menuItemControllers;

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
            summary = "Return menu item by id.",
            description = "Return specific menu items by id for use in building a cart and and creating an order",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A menu-item is returned.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MenuItem.class))),
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

            },
            parameters = {
                    @Parameter(name = "/id", allowEmptyValue = false, required = false,
                            description = "Corresponds to menu item desired (i.e, api/menu-item/1  <- will return " +
                                    "menu-item " +
                                    "limon). Additionally api/menu-item/id?id=1 will return the same value. "),
            }
    )

    @GetMapping("/id")
    @ResponseStatus(code = HttpStatus.OK)
    Optional<Optional<MenuItem>> getById(
            @RequestParam(required = true)
            String id);

//    Optional<Optional<MenuItem>> getById(String id);

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
                            description = "No menu-item found according to input.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

            },
            parameters = {
                    @Parameter(name = "category", allowEmptyValue = false, required = false,
                            description = "Calling menu items by category can be obtained through " +
                                    "api/menu-item/category?category= <your desired item category>."),
            }
    )

    @GetMapping("/category")
    @ResponseStatus(code = HttpStatus.OK)
    List<MenuItem> getByCategory(
            @RequestParam(required = false)
            String category);
}

