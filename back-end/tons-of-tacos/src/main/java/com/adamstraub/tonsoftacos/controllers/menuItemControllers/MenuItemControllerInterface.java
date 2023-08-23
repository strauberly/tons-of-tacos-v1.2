package com.adamstraub.tonsoftacos.controllers.menuItemControllers;

import com.adamstraub.tonsoftacos.entities.MenuItem;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RequestMapping("api/menu")
@OpenAPIDefinition(info = @Info(title = "Services pertaining to menu items for customers."),
        servers = {@Server(url="http://localhost:8080/", description = "Local server")})
public interface MenuItemControllerInterface {
    @Operation(
            summary = "Return menu item by id.",
            description = "For use by customers to view individual items and create an order.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A menu-item is returned.",
                            content = @Content(mediaType = "application/json")),
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
                    @Parameter(name = "id", allowEmptyValue = false, required = false,
                            description = "api/menu/id?id={a valid id number}. Returns a menu item by id number."),
            }
    )

    @GetMapping("/id")
    MenuItem getById(
            @RequestParam Integer id);



    @Operation(
            summary = "Return menu items by category.",
            description = "For use by customers to view menu items by category and create an order.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Menu items returned by category.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No menu-items found according to input. Check formatting",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

            },
            parameters = {
                    @Parameter(name = "category", allowEmptyValue = false, required = false,
                            description = """
                            api/menu/category?category= {your desired menu item category ie. side}
                            Returns menu items by the category side.
                            Current options include taco, side, drink, and topping."""),
            }
    )

    @GetMapping("/category")
    List<MenuItem> getByCategory(
            @RequestParam(required = true)
            String category);
}

