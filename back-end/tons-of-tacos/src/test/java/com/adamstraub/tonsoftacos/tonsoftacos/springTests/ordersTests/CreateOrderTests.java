package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ordersTests;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ordersTestsSupport.OrdersTestsSupport;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

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
        class testThatDoesNotPolluteTheApplicationContext extends OrdersTestsSupport {
            @Test
            void orderCreated201() {
//                Given: a valid order
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
                System.out.println("response: " + response.getBody());

//                Then: an order is successfully stored with a 201 response
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
                System.out.println("Response code is " + response.getStatusCode() + ".");
            }
        }
//        test for invalid
    }