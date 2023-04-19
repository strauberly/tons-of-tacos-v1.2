package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ordersTests;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ordersTestsSupport.OrdersTestsSupport;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CreateOrderTests {
        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Nested
        @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
        @TestPropertySource("classpath:test-application.properties")
        @Sql(scripts = {
                "classpath:/test-schema.sql",
                "classpath:/test-data.sql",
        },
                config = @SqlConfig(encoding = "utf-8"))
        class testThatDoesNotPolluteTheApplicationContextUris extends OrdersTestsSupport {
            @Test
            void orderCreated201() {
//                Given: a valid order and authheader

                // get valid token for authheader
//                String token = validToken();
                String token = encryptedToken();
                Assertions.assertNotNull(token);

//           build authheader
                HttpHeaders authHeader = new HttpHeaders();
                authHeader.setContentType(MediaType.APPLICATION_JSON);
                authHeader.setBearerAuth(token);
                HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);

                String body = validOrderBody();
                System.out.println(body);


//                When: a successful connection is made
                String uri = getBaseUriForCreateOrder();
                System.out.println(uri);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
                ResponseEntity<Orders> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
                        Orders.class);


//                Then: an order is successfully stored with a 201 response
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
                System.out.println("Response code is " + response.getStatusCode() + ".");

//                And: The order is successfully retrieved by the test Uid as verification
                String parameter = "orderUid";
                String testOrderUid = "223-44-444";
                String getOrderUri =
                        String.format("%s?%s=%s", getBaseUriForGetOrderByUid(), parameter, testOrderUid );
                System.out.println(getOrderUri);
                ResponseEntity<OwnersGetOrderDto> orderUidResponse =
                        getRestTemplate().exchange(getOrderUri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                        });
                System.out.println(orderUidResponse.getBody());
                Assertions.assertEquals(testOrderUid, Objects.requireNonNull(orderUidResponse.getBody()).getOrderUid());
            }
        }
//        test for invalid
    }