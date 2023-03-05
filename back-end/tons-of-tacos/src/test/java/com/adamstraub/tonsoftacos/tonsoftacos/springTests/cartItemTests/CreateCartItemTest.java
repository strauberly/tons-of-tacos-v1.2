package com.adamstraub.tonsoftacos.tonsoftacos.springTests.cartItemTests;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.CartItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.OrderItemTestSupport;
import org.junit.jupiter.api.Nested;
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

public class CreateCartItemTest {

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
    class testThatDoesNotPolluteTheApplicationContext extends OrderItemTestSupport {

        @Test
        void createOrderItemWithDto201Response(){
//            Given: a properly formatted order id body
            String body = createValidOrderItemBody();
            System.out.println(body);


//            When: a successful connection is made
            String uri = getBaseUriForCreateOrderItem();
            System.out.println(uri);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
            System.out.println(bodyEntity);
            ResponseEntity<CartItem> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
                    CartItem.class);
            System.out.println("response: " + response.getBody());
//           Then: a response code of 201 is returned and the order id is added to db
            System.out.println("Response code is " + response.getStatusCode() + ".");
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }

        @Test
        void orderItemBodyHasInvalidParameters() {
//      Given: an improperly formatted cart item body
            String body = createInvalidOrderItemBody();
            System.out.println(body);


//            When: a connection is made
            String uri = getBaseUriForCreateOrderItem();
            System.out.println(uri);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
            ResponseEntity<CartItem> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
                    CartItem.class);
            System.out.println(response.getBody());
            System.out.println(response.getStatusCode());
            //Then: item is not created
            System.out.println("Response code is " + response.getStatusCode() + ".");
            System.out.println(Objects.requireNonNull(response.getBody()));
            assertThat(response.getStatusCode().is4xxClientError());
        }
    }
}