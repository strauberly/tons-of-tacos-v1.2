package com.adamstraub.tonsoftacos.tonsoftacos.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@Validated
@RequestMapping("api/menu-item")
@OpenAPIDefinition(info = @Info(title = "Menu Item Service"),
servers = {@Server(url="http://localhost:8080/", description = "Local server")})
public interface MenuItemController {
    @Operation(
            summary = "Return menu item by id",
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
                    @Parameter(name = "/<id>", allowEmptyValue = false, required = false,
                            description = "Corresponds to menu item desired (i.e, api/menu-item/1  <- will return " +
                                    "menu-item " +
                                    "limon). Additionally api/menu-item/search/id?id=6 will return the same value. " +
                                    "Calling menu items by category can be obtained through " +
                                    "api/menu-item/search/category_type?category_type=category name."),
            }
    )
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    MenuItem id(
            @RequestParam(required = false)
            Integer id);
}

