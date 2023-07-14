package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers.session;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.security.OwnerAuthDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;

@Validated
@RequestMapping(
        value = "api/owners-tools"
)
@OpenAPIDefinition(info = @Info(title = "Owners login."),
        servers = {@Server(url="http://localhost:8080", description = "Local server")})
public interface AuthControllerInterface {
    @Operation(
            summary = "Login an owner.",
            description = """
                  This endpoint will allows an owner to login and create a jwt token that will grant access to their
                  other endpoint tools.""",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Owner logged in.",
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

    @PostMapping("/login")
    String ownerLogin(@RequestBody OwnerAuthDto authDto) throws UnsupportedEncodingException;
//
//
}
