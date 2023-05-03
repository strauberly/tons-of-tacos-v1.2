package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ordersTests;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.ReturnOrderToCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ordersTestsSupport.OrdersTestsSupport;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
import org.apache.http.entity.mime.content.StringBody;
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

            String body = genUidBody();
//                String body = validOrderBody();
            System.out.println(body);


//                When: a successful connection is made
            String uri = getBaseUriForCreateOrder();
            System.out.println(uri);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
            ResponseEntity<ReturnOrderToCustomerDto> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
                    ReturnOrderToCustomerDto.class);
            System.out.println("response body: " + response.getBody());
//                System.out.println(Objects.requireNonNull(response.getBody()).getOrderUid());

//                Then: an order is successfully stored with a 201 response
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
            System.out.println("Response code is " + response.getStatusCode() + ".");

//                And: The order is successfully retrieved by the test Uid as verification
            String parameter = "orderUid";
            String testOrderUid = Objects.requireNonNull(response.getBody()).getOrderUuid();
            String getOrderUri =
                    String.format("%s?%s=%s", getBaseUriForGetOrderByUid(), parameter, testOrderUid);
            System.out.println(getOrderUri);
            ResponseEntity<OwnersGetOrderDto> orderUidResponse =
                    getRestTemplate().exchange(getOrderUri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                    });
            System.out.println(orderUidResponse.getBody());
            System.out.println("Response code is " + orderUidResponse.getStatusCode() + ".");
            Assertions.assertEquals(testOrderUid, Objects.requireNonNull(orderUidResponse.getBody()).getOrderUid());
        }

//        test for invalid

        @Test
        void invalidOrder400() {
//        Given: an invalid order(ie incomplete. missing fields etc) but valid auth header
            // get valid token for authheader
//                String token = validToken();
            String token = encryptedToken();
            Assertions.assertNotNull(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);

            String body = invalidBody();
//                String body = validOrderBody();
            System.out.println(body);


//                When: a successful connection is made
            String uri = getBaseUriForCreateOrder();
            System.out.println(uri);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
            ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, new ParameterizedTypeReference<>() {
            });
//        Then: a 400 bad request is returned
            System.out.println("Response code is " + response.getStatusCode() + ".");
            System.out.println("response body: " + response.getBody());
            Assertions.assertSame(response.getStatusCode(), HttpStatus.BAD_REQUEST);

//    And: the error message contains
            Map<String, Object> error = response.getBody();
            assert error != null;
            Assertions.assertEquals(error.get("status code").toString().substring(0,3), HttpStatus.BAD_REQUEST.toString().substring(0,3));
            Assertions.assertTrue(error.containsValue("/api/order/checkout"));
            Assertions.assertTrue(error.containsKey("message"));
            Assertions.assertTrue(error.containsKey("timestamp"));


        }
    }
}