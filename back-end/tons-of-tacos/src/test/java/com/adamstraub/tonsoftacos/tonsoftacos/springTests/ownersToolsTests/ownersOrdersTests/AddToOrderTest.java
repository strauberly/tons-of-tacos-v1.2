package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersOrdersTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
        //        Given: a valid order, menu item, quantity
        @Test
        void addItemToOrder200() {
            int orderId = 1;
            int menuitemId = 4;
            int quantity = 2;
//            call order item before alteration
            String parameter = "orderId";
            String getOrderUri =
                    String.format("%s?%s=%d", getBaseUriForGetOrderById(), parameter, orderId);
            System.out.println(getOrderUri);

            ResponseEntity<OwnersGetOrderDto> getOrderResponse =
                    getRestTemplate().exchange(getOrderUri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });

            System.out.println(getOrderResponse.getBody());
            System.out.println("Number of items in order: " + Objects.requireNonNull(getOrderResponse.getBody()).getOrderItems().toArray().length);
//        When:  a successful connection made
            String uri =
                    String.format("%s/%d/%d/%d", getBaseUriForAddOrderItem(), orderId, menuitemId, quantity);
            System.out.println(uri);

            ResponseEntity<OwnersGetOrderDto> response =
                    getRestTemplate().exchange(uri, HttpMethod.PATCH, null,
                            new ParameterizedTypeReference<>() {});
//        Then:  response will return 200
            assertThat(response.getStatusCode().equals(HttpStatus.OK));
            System.out.println(response.getStatusCode());
//        And:   length of order items will have increased
            String getOrderUri2 =
                    String.format("%s?%s=%d", getBaseUriForGetOrderById(), parameter, orderId);
            System.out.println(getOrderUri2);

            ResponseEntity<OwnersGetOrderDto> getOrderResponse2 =
                    getRestTemplate().exchange(getOrderUri2, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });

            System.out.println("Number of items in order: " + Objects.requireNonNull(getOrderResponse2.getBody()).getOrderItems().toArray().length);

            assertThat(getOrderResponse2.getBody().getOrderItems().toArray().length > getOrderResponse.getBody().getOrderItems().toArray().length);
            System.out.println("Order size has increased");
            //        And:   order total will have increased
            assertThat(getOrderResponse2.getBody().getOrderTotal() > getOrderResponse.getBody().getOrderTotal());
            System.out.println("Order total has increased by: " + (getOrderResponse2.getBody().getOrderTotal() - getOrderResponse.getBody().getOrderTotal()));
        }
    }
}
