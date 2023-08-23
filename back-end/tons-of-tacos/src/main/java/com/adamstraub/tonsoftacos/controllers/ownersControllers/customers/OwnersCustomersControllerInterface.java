package com.adamstraub.tonsoftacos.controllers.ownersControllers.customers;

import com.adamstraub.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.dto.businessDto.CustomerReturnedToOwner;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@Validated
@RequestMapping(
        value = "api/owners-tools/customers")
@OpenAPIDefinition(info = @Info(title = "services pertaining to functions reserved for the owners of tons of tacos."),
        servers = {@Server(url="http://localhost:8080", description = "Local server")})
public interface OwnersCustomersControllerInterface {

    // get all customers
    @Operation(
            summary = "All customers returned.",
            description = """
                    For owner use only with proper auth.""",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "All customers returned.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No customers found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @Transactional
    @GetMapping("/get-customers")
    List<CustomerReturnedToOwner> getAllCustomers();

    //  get a customer by customer name
    @Operation(
            summary = "A customer is returned by customer name.",
            description = """
                  For owner use only with proper auth.""",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer is returned.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No customer found.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @Transactional
    @GetMapping("/get-customer/name")
    CustomerReturnedToOwner getCustomerByName(@RequestParam String name) throws Exception;

//    get customer by id
@Operation(
        summary = "A customer is returned by id.",
        description = """
                  For owner use only with proper auth.""",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Customer is returned.",
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(
                        responseCode = "400",
                        description = "Request parameters invalid.",
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(
                        responseCode = "404",
                        description = "No customers found.",
                        content = @Content(mediaType = "application/json")),
                @ApiResponse(
                        responseCode = "500",
                        description = "An unplanned error occured.",
                        content = @Content(mediaType = "application/json")),
        }
)
@Transactional
@GetMapping("/get-customer/customerId")
CustomerReturnedToOwner getCustomerById(@RequestParam Integer customerId);


//edit customer name
    @Operation(
            summary = "Updates a customer name.",
            description = "For owner use only with proper auth.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer name updated.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No customer found for given parameter.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @Transactional
    @PutMapping("/edit-customer-name/{customerId}/{newCustomerName}")
    String updateCustomerName(
            @PathVariable
            Integer customerId,
            @PathVariable
            String newCustomerName);


//edit customer email
    @Operation(
            summary = "Updates a customer's email.",
            description = "For owner use only with proper auth.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer email updated.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No customer found according to parameter.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @Transactional
    @PutMapping("/edit-customer-email/{customerId}/{newCustomerEmail}")
    String updateCustomerEmail(
            @PathVariable
            Integer customerId,
            @PathVariable
            String newCustomerEmail);



    //edit customer phone number
    @Operation(
            summary = "Updates a customer's phone number.",
            description = "For owner use only with proper auth.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer phone number updated.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No customer found according to parameter.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),
            }
    )
    @Transactional
    @PutMapping("/edit-customer-phone/{customerId}/{newCustomerPhone}")
    String updateCustomerPhone(
            @PathVariable
            Integer customerId,
            @PathVariable
            String newCustomerPhone);

    // delete customer by id
    @Operation(
            summary = "Deletes a customer by id.",
            description = "For owner use only with proper auth.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Customer deleted.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderItem.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters invalid.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No customer found according to parameter.",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occured.",
                            content = @Content(mediaType = "application/json")),

            }
    )
    @Transactional
    @DeleteMapping("/delete-customer/{customerId}")
    String deleteCustomer(@PathVariable Integer customerId);
}
