package com.adamstraub.tonsoftacos.tonsoftacos.springTests.orderItemTests;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.OrderItemTestSupport;
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

import static org.assertj.core.api.Assertions.assertThat;

class UpdateOrderItemQuantityTests {
     @Autowired
    JdbcTemplate jdbcTemplate;

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    @TestPropertySource(locations = "/application.properties")
    @TestPropertySource("classpath:application.properties")
    @Sql(scripts = {
            "classpath:/test-schema.sql",
            "classpath:/test-data.sql",
    },
            config = @SqlConfig(encoding = "utf-8"))
    class testThatDoesNotPolluteTheApplicationContext extends OrderItemTestSupport {


        @Test
        void updateOrderItemQuantityById200(){
//            Given: a valid order id id
            int orderItemId = 2;
            int newQuantity = 4;

//            When: a successful connection is made
            String uri =
                    String.format("%s/%d/%d", getBaseUriForUpdateOrderItem(), orderItemId, newQuantity);
            System.out.println(uri);


//          update response
            ResponseEntity<OrderItem> updatedOrderItemResponse =
                    getRestTemplate().exchange(uri, HttpMethod.PATCH, null,
                            new ParameterizedTypeReference<>() {});
            System.out.println(updatedOrderItemResponse.getBody());
//            menu id call to provide data for comparison
            String parameter = "id";
            int menuItemId = 2;
            String menuItemUri =
                    String.format("%s?%s=%d",
                            getBaseUriForMenuItemByIdQuery(),
                            parameter,
                            Objects.requireNonNull(updatedOrderItemResponse.getBody()).getItemId().getId());
//                            menuItemId);
            System.out.println(uri);

            ResponseEntity<MenuItem> menuItemResponse =
                            getRestTemplate().exchange(menuItemUri,
                                    HttpMethod.GET,
                                    null,
                                    new ParameterizedTypeReference<>() {
                            });

//            Then: a response status of 200 is returned
            System.out.println("Response code is " + updatedOrderItemResponse.getStatusCode() + ".");
            assertThat(updatedOrderItemResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//
////            And: order id total is updated appropriately for the new quantity * the id unit price
            System.out.println("menu id base price: " +
                    Objects.requireNonNull(menuItemResponse.getBody()).getUnitPrice() + " *" + " new " +
                    "quantity: " + newQuantity + " = " + updatedOrderItemResponse.getBody().getTotal());
            System.out.println(menuItemResponse.getBody());
            System.out.println(updatedOrderItemResponse.getBody());
            assertThat(Objects.requireNonNull(updatedOrderItemResponse.getBody()).getTotal() ==
                    menuItemResponse.getBody().getUnitPrice() * newQuantity);
        }


        @Test
        void failedUpdateOrderItemQuantityById404(){
//            Given: an invalid order id id
            int orderItemId = 62;
            int newQuantity = 4;

//            When: a successful connection is made
            String uri =
                    String.format("%s/%d/%d", getBaseUriForUpdateOrderItem(), orderItemId, newQuantity);
            System.out.println(uri);


//          update response
            ResponseEntity<OrderItem> updatedOrderItemResponse =
                    getRestTemplate().exchange(uri, HttpMethod.PATCH, null,
                            new ParameterizedTypeReference<>() {});

//            Then: a response status of 404 is returned
            System.out.println("Response code is " + updatedOrderItemResponse.getStatusCode() + ".");
            assertThat(updatedOrderItemResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
        @Test
        void orderItemDeletedForZeroQuantity200(){
//            Given: a valid order id id and quantity of zero
            int orderItemId = 2;
            int newQuantity = 0;

//            When: a successful connection is made
            String uri =
                    String.format("%s/%d/%d", getBaseUriForUpdateOrderItem(), orderItemId, newQuantity);
            System.out.println(uri);
            ResponseEntity<OrderItem> response =
                    getRestTemplate().exchange(uri, HttpMethod.PATCH, null,
                            new ParameterizedTypeReference<>() {});
//            Then: a response status of 200 is returned for deleted
            System.out.println("Response code is " + response.getStatusCode() + ".");
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//            And: a repeat call produces 404 because id is deleted for having quantity zero and can not be updated
            ResponseEntity<OrderItem> response2 =
                    getRestTemplate().exchange(uri, HttpMethod.PATCH, null,
                            new ParameterizedTypeReference<>() {});
            System.out.println("Response code is " + response2.getStatusCode() + ".");
            assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
