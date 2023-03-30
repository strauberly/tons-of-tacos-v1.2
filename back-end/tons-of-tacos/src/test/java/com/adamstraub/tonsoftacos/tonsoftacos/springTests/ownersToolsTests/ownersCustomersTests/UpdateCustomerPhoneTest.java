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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
//            Given: a valid order, order item, and new quantity.
                int customerId = 1;
                String newCustomerPhone = "555.555.5155";
                String parameter = "customerId";
//             get order before alteration

                String getCustomerUri =
                        String.format("%s?%s=%d", getBaseUriForGetCustomerById(),parameter, customerId);
                System.out.println(getCustomerUri);
//            call order item before alteration
                ResponseEntity<OwnersGetCustomerDto> getCustomerResponse =
                        getRestTemplate().exchange(getCustomerUri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
                System.out.println(Objects.requireNonNull(getCustomerResponse.getBody()).getPhone());

//            When: a successful connection is made
                String uri =
                        String.format("%s/%d/%s",  getBaseUriForUpdatePhone(), customerId, newCustomerPhone);
                System.out.println(uri);
//
                ResponseEntity<String> response =
                        getRestTemplate().exchange(uri, HttpMethod.PATCH, null,
                                new ParameterizedTypeReference<>() {});

                //            Then: a response of 200 returned
                Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
                System.out.println(response.getStatusCode());
//            And: when the order called the order item quantity, total, and order total should not be the same as
//            before
//            call order item after alteration
                String getCustomerUri2 =
                        String.format("%s?%s=%d", getBaseUriForGetCustomerById(), parameter, customerId);
                System.out.println(getCustomerUri2);

                ResponseEntity<OwnersGetOrderDto> getCustomerResponse2 =
                        getRestTemplate().exchange(getCustomerUri2, HttpMethod.GET, null,
                                new ParameterizedTypeReference<>() {
                                });
                System.out.println(Objects.requireNonNull(getCustomerResponse2.getBody()).getPhone());
                Assertions.assertNotEquals(getCustomerResponse.getBody().getPhone(),
                        getCustomerResponse2.getBody().getPhone());
                System.out.println("Customer phone changed.");
            }
        }
    }
