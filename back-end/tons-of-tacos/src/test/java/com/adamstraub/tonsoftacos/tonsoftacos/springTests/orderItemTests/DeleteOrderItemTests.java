package com.adamstraub.tonsoftacos.tonsoftacos.springTests.orderItemTests;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DeleteOrderItemTests {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @TestPropertySource("classpath:application.properties")
    @Sql(scripts = {
            "classpath:/test-schema.sql",
            "classpath:/test-data.sql",
    },
            config = @SqlConfig(encoding = "utf-8"))
    class testThatDoesNotPolluteTheApplicationContext extends OrderItemTestSupport {
    @Test
        void deleteOrderItemById(){
//        Given: a valid order id
        int orderItemId = 2;
//        When: a successful connection is made
        String uri =
                String.format("%s/%d", getBaseUriForRemoveOrderItem(), orderItemId);
        System.out.println(uri);

        ResponseEntity<OrderItem> response =
                getRestTemplate().exchange(uri,
                        HttpMethod.DELETE,
                        null,
                        new ParameterizedTypeReference<>() {});
//        Then: a 200 status is complete
        System.out.println("Response code is " + response.getStatusCode() + ".");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

////        And: deletion verified by trying to delete again but receiving 404 for order id not found.
        ResponseEntity<OrderItem> response2 =
                getRestTemplate().exchange(uri,
                        HttpMethod.DELETE,
                        null,
                        new ParameterizedTypeReference<>() {});
        System.out.println("code is " + response2.getStatusCode()+". Item has been deleted and cannot be deleted " +
                "again.");
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);


    }
        @Test
        void deleteOrderItemByInvalidId404(){
//        Given: a valid order id id
            int orderItemId = 47;
//        When: a successful connection is made
            String uri =
                    String.format("%s/%d", getBaseUriForRemoveOrderItem(), orderItemId);
            System.out.println(uri);

            ResponseEntity<OrderItem> response =
                    getRestTemplate().exchange(uri,
                            HttpMethod.DELETE,
                            null,
                            new ParameterizedTypeReference<>() {});
//        Then: a 4xx status is returned
            System.out.println("Response code is " + response.getStatusCode() + ". Order item not deleted.");
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}
