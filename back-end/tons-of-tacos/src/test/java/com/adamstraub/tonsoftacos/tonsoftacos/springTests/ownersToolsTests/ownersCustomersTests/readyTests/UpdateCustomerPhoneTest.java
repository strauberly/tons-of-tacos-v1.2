package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersCustomersTests.readyTests;

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
                ResponseEntity<OwnersGetCustomerDto> getCustomerResponse =
                        getRestTemplate().exchange(getCustomerUri, HttpMethod.GET, headersEntity, new ParameterizedTypeReference<>() {
                        });
                System.out.println(Objects.requireNonNull(getCustomerResponse.getBody()).getPhone());

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

                ResponseEntity<OwnersGetOrderDto> getCustomerResponse2 =
                        getRestTemplate().exchange(getCustomerUri2, HttpMethod.GET, headersEntity,
                                new ParameterizedTypeReference<>() {
                                });
                System.out.println(Objects.requireNonNull(getCustomerResponse2.getBody()).getPhone());
                Assertions.assertNotEquals(getCustomerResponse.getBody().getPhone(),
                        getCustomerResponse2.getBody().getPhone());

            }
        }
    }
