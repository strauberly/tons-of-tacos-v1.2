package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersCustomersTests;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
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



public class UpdateCustomerNameTest {
   
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
            void updateCustomerName200() {
                //            get valid token
//                String token = validToken();
                String token = encryptedToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------
//            Given: a customerId, new name and valid auth header.
                int customerId = 1;
                String newCustomerName = "Larry Lawson";
                String parameter = "customerId";

                HttpHeaders authHeader = new HttpHeaders();
                authHeader.setContentType(MediaType.APPLICATION_JSON);
                authHeader.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(authHeader);

//
//             get customer before alteration
                String getCustomerUri =
                        String.format("%s?%s=%d", getBaseUriForGetCustomerById(),parameter, customerId);
                System.out.println(getCustomerUri);
//            call order item before alteration
                ResponseEntity<OwnersGetCustomerDto> getCustomerResponse =
                        getRestTemplate().exchange(getCustomerUri, HttpMethod.GET, headersEntity, new ParameterizedTypeReference<>() {
                        });
                System.out.println(Objects.requireNonNull(getCustomerResponse.getBody()).getName());

//            When: a successful connection is made
                String uri =
                        String.format("%s/%d/%s",  getBaseUriForUpdateName(), customerId, newCustomerName);
                System.out.println(uri);
//
                ResponseEntity<String> response =
                        getRestTemplate().exchange(uri, HttpMethod.PUT, headersEntity,
                                new ParameterizedTypeReference<>() {});

                //            Then: a response of 200 returned
                Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
                System.out.println(response.getStatusCode());
                System.out.println("Customer name changed.");

//            And: when the customer called again the name should not be the same.

//            call customer after alteration
                String getCustomerUri2 =
                        String.format("%s?%s=%d", getBaseUriForGetCustomerById(), parameter, customerId);
                System.out.println(getCustomerUri2);

                ResponseEntity<OwnersGetOrderDto> getCustomerResponse2 =
                        getRestTemplate().exchange(getCustomerUri2, HttpMethod.GET, headersEntity,
                                new ParameterizedTypeReference<>() {
                        });
                System.out.println(Objects.requireNonNull(getCustomerResponse2.getBody()).getName());
                Assertions.assertNotEquals(getCustomerResponse.getBody().getName(),
                        getCustomerResponse2.getBody().getName());
            }


            @Test
            void customerNameNotUpdatedInvalidName400() {
                //            get valid token
//                String token = validToken();
                String token = encryptedToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------
//            Given: a valid customerId, invalid name and valid auth header.
                int customerId = 1;
                String newCustomerName = "dsfk jh!@#";
                String parameter = "customerId";

                HttpHeaders authHeader = new HttpHeaders();
                authHeader.setContentType(MediaType.APPLICATION_JSON);
                authHeader.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(authHeader);

//            When: a successful connection is made
                String uri =
                        String.format("%s/%d/%s",  getBaseUriForUpdateName(), customerId, newCustomerName);
                System.out.println(uri);
//
                ResponseEntity<Map<String, Object>> response =
                        getRestTemplate().exchange(uri, HttpMethod.PUT, headersEntity,
                                new ParameterizedTypeReference<>() {});

//            Then: a response of 400 returned
                Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                System.out.println("Response code is " + response.getStatusCode() + ".");
                System.out.println(response.getBody());
                System.out.println(response.getBody().get("uri").toString());

//        And: the error message contains
                Map<String, Object> error = response.getBody();
                assert error != null;
                Assertions.assertEquals(error.get("status code").toString().substring(0,3), HttpStatus.BAD_REQUEST.toString().substring(0,3));
                Assertions.assertTrue(error.containsValue("/api/owners-tools/customers/edit-customer-name/1/dsfk%20jh!@"));
                Assertions.assertTrue(error.containsKey("message"));
                Assertions.assertTrue(error.containsKey("timestamp"));
                System.out.println("Negative test case complete for changing the name of an existing customer.");
            }

            @Test
            void customerNameNotUpdatedNoSuchCustomer404() {
                //            get valid token
//                String token = validToken();
                String token = encryptedToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------
//            Given: an invalid customerId, valid name and valid auth header.
                int customerId = 54;
                String newCustomerName = "Gary Garrison";
                String parameter = "customerId";

                HttpHeaders authHeader = new HttpHeaders();
                authHeader.setContentType(MediaType.APPLICATION_JSON);
                authHeader.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(authHeader);

//            When: a successful connection is made
                String uri =
                        String.format("%s/%d/%s",  getBaseUriForUpdateName(), customerId, newCustomerName);
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

//        And: the error message contains
                Map<String, Object> error = response.getBody();
                assert error != null;
                Assertions.assertEquals(error.get("status code").toString().substring(0,3), HttpStatus.NOT_FOUND.toString().substring(0,3));
                Assertions.assertTrue(error.containsValue("/api/owners-tools/customers/edit-customer-name/54/Gary%20Garrison"));
                Assertions.assertTrue(error.containsKey("message"));
                Assertions.assertTrue(error.containsKey("timestamp"));
                System.out.println("Negative test case complete for changing the name of an existing customer.");
            }
        }
    }


