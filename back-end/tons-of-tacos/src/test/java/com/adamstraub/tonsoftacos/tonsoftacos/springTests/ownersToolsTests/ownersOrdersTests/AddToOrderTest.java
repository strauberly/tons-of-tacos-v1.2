package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersOrdersTests;

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

public class AddToOrderTest {

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
        void addItemToOrder200() {
//      Given: a valid orderId, valid menuItemId and  authheader.
//            get valid token
            String token = encryptedToken();
            Assertions.assertNotNull(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);

            int orderId = 1;
            int menuItemId = 4;
            int quantity = 2;

//            call order before alteration
            String parameter = "orderId";
            String getOrderUri =
                    String.format("%s?%s=%d", getBaseUriForGetOrderById(), parameter, orderId);
            System.out.println(getOrderUri);

            ResponseEntity<OwnersGetOrderDto> getOrderResponse =
                    getRestTemplate().exchange(getOrderUri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                    });

            System.out.println(getOrderResponse.getBody());
            System.out.println("Number of items in order: " + Objects.requireNonNull(getOrderResponse.getBody()).getOrderItems().toArray().length);

            //        When:  a successful connection made
            String uri =
                    String.format("%s/%d/%d/%d", getBaseUriForAddOrderItem(), orderId, menuItemId, quantity);
            System.out.println(uri);

            ResponseEntity<String> response =
                    getRestTemplate().exchange(uri, HttpMethod.PUT, headerEntity,
                            new ParameterizedTypeReference<>() {});
//        Then:  response will return 200
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());

//        And:   number of items in the order will have increased
            String getOrderUri2 =
                    String.format("%s?%s=%d", getBaseUriForGetOrderById(), parameter, orderId);
            System.out.println(getOrderUri2);

            ResponseEntity<OwnersGetOrderDto> getOrderResponse2 =
                    getRestTemplate().exchange(
                            getOrderUri2, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                    });

            System.out.println("Number of items in order: " +
                    Objects.requireNonNull(getOrderResponse2.getBody()).getOrderItems().toArray().length);
            Assertions.assertTrue(
                    getOrderResponse2.getBody()
                            .getOrderItems().toArray().length >
                            getOrderResponse.getBody().getOrderItems().toArray().length);

            System.out.println("Order size has increased");

            //        And:   order total will have increased
            Assertions.assertTrue(getOrderResponse2.getBody().getOrderTotal() >
                    getOrderResponse.getBody().getOrderTotal());
            System.out.println("Order total has increased by: " +
                    (getOrderResponse2.getBody().getOrderTotal() - getOrderResponse.getBody().getOrderTotal()));
        }
        @Test
        void menuItemToAddToOrderInvalid404() {
//      Given: a valid orderId, invalid menuItemId and  valid authheader.
//            get valid token
            String token = encryptedToken();
            Assertions.assertNotNull(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);

            int orderId = 1;
            int menuItemId = 66;
            int quantity = 2;

            //        When:  a successful connection made
            String uri =
                    String.format("%s/%d/%d/%d", getBaseUriForAddOrderItem(), orderId, menuItemId, quantity);
            System.out.println(uri);

            ResponseEntity<Map<String, Object>> response =
                    getRestTemplate().exchange(uri, HttpMethod.PUT, headerEntity,
                            new ParameterizedTypeReference<>() {});
//        Then:  response will return 404
            Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            System.out.println("Response code is " + response.getStatusCode() + ".");
            System.out.println("Response body: " + response.getBody());

//        And: the error message contains
            Map<String, Object> error = response.getBody();
            assert error != null;
            Assertions.assertEquals(error.get("status code").toString().substring(0,3), HttpStatus.NOT_FOUND.toString().substring(0,3));
            Assertions.assertTrue(error.containsValue("/api/owners-tools/orders/add-to-order/1/66/2"));
            Assertions.assertTrue(error.containsKey("message"));
            Assertions.assertTrue(error.containsKey("timestamp"));
            System.out.println("Negative test case complete for add invalid menu item to order.");

        }

        @Test
        void orderToBeAddedToInvalid404() {
//      Given: an invalid orderId, valid menuItemId and  valid authheader.
//            get valid token
            String token = encryptedToken();
            Assertions.assertNotNull(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);

            int orderId = 78;
            int menuItemId = 2;
            int quantity = 2;

            //        When:  a successful connection made
            String uri =
                    String.format("%s/%d/%d/%d", getBaseUriForAddOrderItem(), orderId, menuItemId, quantity);
            System.out.println(uri);

            ResponseEntity<Map<String, Object>> response =
                    getRestTemplate().exchange(uri, HttpMethod.PUT, headerEntity,
                            new ParameterizedTypeReference<>() {});
//        Then:  response will return 404
            Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            System.out.println("Response code is " + response.getStatusCode() + ".");
            System.out.println("Response body: " + response.getBody());

//        And: the error message contains
            Map<String, Object> error = response.getBody();
            assert error != null;
            Assertions.assertEquals(error.get("status code").toString().substring(0,3), HttpStatus.NOT_FOUND.toString().substring(0,3));
            Assertions.assertTrue(error.containsValue("/api/owners-tools/orders/add-to-order/78/2/2"));
            Assertions.assertTrue(error.containsKey("message"));
            Assertions.assertTrue(error.containsKey("timestamp"));
            System.out.println("Negative test case complete for attempt to add item to invalid order.");

        }

    }
}
