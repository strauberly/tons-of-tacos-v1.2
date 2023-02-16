package com.adamstraub.tonsoftacos.tonsoftacos.springTests.orderItemTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.CreateOrderItemTestSupport;
import org.hibernate.TypeMismatchException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateOrderItemTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @TestPropertySource("classpath:application-test.properties")
    @Sql(scripts = {
            "classpath:/test-schema.sql",
            "classpath:/test-data.sql",
    },
            config = @SqlConfig(encoding = "utf-8"))
    class testThatDoesNotPolluteTheApplicationContext extends CreateOrderItemTestSupport {
        MenuItemRepository menuItemRepository;

        @Test
        void createOrderItemWith201Response() {

//            Given: a properly formatted order item body
            String body = createOrderItemBody();
            System.out.println(body);


//            When: a successful creation is made
            String uri = getBaseUriForCreateOrderItem();
            System.out.println(uri);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
            ResponseEntity<OrderItem> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
                    OrderItem.class);
//           Then: a response code of 201 is returned and the order item is added to db
            System.out.println("Response code is " + response.getStatusCode() + ".");
            System.out.println("Added an item to the cart with an id of  : " + Objects.requireNonNull(response.getBody()).getOrderUuid());
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }

        @Test
        void orderItemBodyHasInvalidParameters() {
//      Given: a properly formatted order item body
            String body = createOrderItemBody();
            System.out.println(body);

//            When: a successful creation is made
            String uri = getBaseUriForCreateOrderItem();
            System.out.println(uri);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
            ResponseEntity<OrderItem> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
                    OrderItem.class);

//           Then: a response code of 404 is returned if menu item does not exist or a response of
//        <> if the input is bad
//        System.out.println();
            System.out.println("Response code is " + response.getStatusCode() + ".");
//            System.out.println("Unable to process entity. Consult the docs and verify input against required parameters.");
            if (Objects.requireNonNull(response.getBody()).getItemId() > menuItemRepository.count()) {
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            }
            if (!response.getBody().getItemId().getClass().toString().equals("Integer")){
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }
            if (!response.getBody().getOrderUuid().matches("/[0-9-]/g")) {
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }
            if (!response.getBody().getQuantity().getClass().toString().equals("Integer")) {
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            } else if (!response.getBody().getTotal().getClass().toString().equals("Double")) {
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
