package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersCustomersTests;

import com.adamstraub.tonsoftacos.dto.businessDto.CustomerReturnedToOwner;
import com.adamstraub.tonsoftacos.dto.businessDto.OrderReturnedToOwner;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.Map;
import java.util.Objects;

public class UpdateCustomerPhoneTest {


        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Nested
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        @TestPropertySource("classpath:/test-application.properties")
        @Sql(scripts = {
                "classpath:/test-schema.sql",
                "classpath:/test-data.sql",
        },
                config = @SqlConfig(encoding = "utf-8"))
        class testThatDoesNotPolluteTheApplicationContextUris extends OwnersToolsTestsSupport {

            @Test
            void updateCustomerPhone200() {
                //            get valid token
//                String token = validToken();
                String token = encryptedToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------

//            Given: a valid customer id, phone number and valid auth header
                int customerId = 1;
                String newCustomerPhone = "555.555.5155";
                String parameter = "customerId";
//             get order before alteration

                HttpHeaders authHeader = new HttpHeaders();
                authHeader.setContentType(MediaType.APPLICATION_JSON);
                authHeader.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(authHeader);



                String getCustomerUri =
                        String.format("%s?%s=%d", getBaseUriForGetCustomerById(),parameter, customerId);
                System.out.println(getCustomerUri);
//            call customer before alteration
                ResponseEntity<CustomerReturnedToOwner> getCustomerResponse =
                        getRestTemplate().exchange(getCustomerUri, HttpMethod.GET, headersEntity, new ParameterizedTypeReference<>() {
                        });
                System.out.println("Old phone: " + Objects.requireNonNull(getCustomerResponse.getBody()).getPhone());

//            When: a successful connection is made
                String uri =
                        String.format("%s/%d/%s",  getBaseUriForUpdatePhone(), customerId, newCustomerPhone);
                System.out.println(uri);
//
                ResponseEntity<String> response =
                        getRestTemplate().exchange(uri, HttpMethod.PUT, headersEntity,
                                new ParameterizedTypeReference<>() {});

                //            Then: a response of 200 returned
                Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
                System.out.println(response.getStatusCode());
                System.out.println("Customer phone changed.");
//            And: when the customer is called the phone number should not be the same.

//            call customer after alteration
                String getCustomerUri2 =
                        String.format("%s?%s=%d", getBaseUriForGetCustomerById(), parameter, customerId);
                System.out.println(getCustomerUri2);

                ResponseEntity<OrderReturnedToOwner> getCustomerResponse2 =
                        getRestTemplate().exchange(getCustomerUri2, HttpMethod.GET, headersEntity,
                                new ParameterizedTypeReference<>() {
                                });
                System.out.println("New phone: " + Objects.requireNonNull(getCustomerResponse2.getBody()).getPhone());
                Assertions.assertNotEquals(getCustomerResponse.getBody().getPhone(),
                        getCustomerResponse2.getBody().getPhone());

            }

            @Test
            void customerPhoneNotUpdatedNoSuchCustomer404() {
                //            get valid token
//                String token = validToken();
                String token = encryptedToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------

//            Given: an invalid customer id, valid phone number and valid auth header
                int customerId = 14;
                String newCustomerPhone = "555.555.5156";
                String parameter = "customerId";
//             get order before alteration

                HttpHeaders authHeader = new HttpHeaders();
                authHeader.setContentType(MediaType.APPLICATION_JSON);
                authHeader.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(authHeader);

//            When: a successful connection is made
                String uri =
                        String.format("%s/%d/%s",  getBaseUriForUpdatePhone(), customerId, newCustomerPhone);
                System.out.println(uri);
//
                ResponseEntity<Map<String, Object>> response =
                        getRestTemplate().exchange(uri, HttpMethod.PUT, headersEntity,
                                new ParameterizedTypeReference<>() {});

//            Then: a response of 404 returned
                Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                System.out.println("Response code is " + response.getStatusCode() + ".");
                System.out.println(response.getBody());
                System.out.println(response.getBody().get("uri").toString());

//            And: the error message contains
                Map<String, Object> error = response.getBody();
                assert error != null;
                Assertions.assertEquals(error.get("status code").toString().substring(0,3), HttpStatus.NOT_FOUND.toString().substring(0,3));
                Assertions.assertTrue(error.containsValue("/api/owners-tools/customers/edit-customer-phone/14/555.555.5156"));
                Assertions.assertTrue(error.containsKey("message"));
                Assertions.assertTrue(error.containsKey("timestamp"));
                System.out.println("Negative test case complete for changing the name of an existing customer.");


            }
        }
    }
