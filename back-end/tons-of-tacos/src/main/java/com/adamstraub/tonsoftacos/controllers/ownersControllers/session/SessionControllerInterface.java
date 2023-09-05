package com.adamstraub.tonsoftacos.controllers.ownersControllers.session;

import com.adamstraub.tonsoftacos.dto.businessDto.security.OwnerAuth;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
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
public interface SessionControllerInterface {
    @Operation(
            summary = "Login an owner.",
            description = """
                  This endpoint allows an owner to login and create a jwt token that will grant access to their
                  endpoint tools. Note that password and username have already been encoded. Contact lead engineer
                  for encryption algorithm details in order to create a successful request. Login endpoint currently for
                  owner use only after an entity has been created for them."""
            + "\n" + "\n" + "Sample request body: " + "\n" + "\n" +
                    """
                            {
                                "username":"m)Km7y{f0~nd$,hvNLOw0.F5FlP5u?5",
                                "psswrd":"?aNwlfCd7glf(E&r)lLr}W?fT#Ld?aFw_ic"
                            }
                            """,
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Owner logged in."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No orders found."),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured."),
            }
    )

    @PostMapping("/login")
    String ownerLogin(@RequestBody OwnerAuth authDto) throws UnsupportedEncodingException;
}
