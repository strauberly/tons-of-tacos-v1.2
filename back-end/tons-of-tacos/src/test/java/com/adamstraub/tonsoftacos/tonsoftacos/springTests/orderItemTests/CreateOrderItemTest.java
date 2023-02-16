package com.adamstraub.tonsoftacos.tonsoftacos.springTests.orderItemTests;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.CreateOrderItemTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateOrderItemTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @TestPropertySource("classpath:application-test.properties")
    @Sql(scripts = {
            "classpath:/schema.sql",
            "classpath:/data.sql",
    },
            config = @SqlConfig(encoding = "utf-8"))
    static
    class testThatDoesNotPolluteTheApplicationContext extends CreateOrderItemTestSupport {
        @Test
        void  createOrderItemWith201Response(){
            String body = createOrderItemBody();
            String uri = getBaseUriForCreateOrderItem();

            System.out.println(uri);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
            ResponseEntity<OrderItem> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
                    OrderItem.class);
            System.out.println("Response code is " + response.getStatusCode() + ".");
            System.out.println("Added an item to the cart with an id of  : " + Objects.requireNonNull(response.getBody()).getOrderUuid());
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }
    }
}
