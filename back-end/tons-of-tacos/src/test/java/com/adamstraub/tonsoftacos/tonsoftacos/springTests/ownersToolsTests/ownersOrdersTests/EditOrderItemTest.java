package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersOrdersTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
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

public class EditOrderItemTest {

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
    class testThatDoesNotPolluteTheApplicationContext extends OwnersToolsTestsSupport {

        @Test
        void editOrderItem200() {
//            Given: a valid order, order item, and new quantity.
                int orderId = 1;
                int orderItemId = 3;
                int newQuantity = 2;
//             get order before alteration
            String parameter = "orderId";
            String getOrderUri =
                    String.format("%s?%s=%d", getBaseUriForGetOrderById(), parameter, orderId);
            System.out.println(getOrderUri);
//            call order item before alteration
            ResponseEntity<OwnersGetOrderDto> getOrderResponse =
                    getRestTemplate().exchange(getOrderUri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            System.out.println(Objects.requireNonNull(Objects.requireNonNull(getOrderResponse.getBody()).getOrderItems().get(0)));

//            When: a successful connection is made
            String uri =
                    String.format("%s/%d/%d/%d", getBaseUriForEditOrderItem(), orderId, orderItemId, newQuantity);
            System.out.println(uri);
//
            ResponseEntity<OwnersGetOrderDto> response =
                    getRestTemplate().exchange(uri, HttpMethod.PATCH, null,
                            new ParameterizedTypeReference<>() {});

            //            Then: a response of 200 returned
            assertThat(response.getStatusCode().equals(HttpStatus.OK));
            System.out.println(response.getStatusCode());
//            And: when the order called the order item quantity, total, and order total should not be the same as
//            before
//            call order item after alteration
            String getOrderUri2 =
                    String.format("%s?%s=%d", getBaseUriForGetOrderById(), parameter, orderId);
            System.out.println(getOrderUri2);

            ResponseEntity<OwnersGetOrderDto> getOrderResponse2 =
                    getRestTemplate().exchange(getOrderUri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            System.out.println(Objects.requireNonNull(Objects.requireNonNull(getOrderResponse2.getBody()).getOrderItems().get(0)));
            assertThat(!getOrderResponse.getBody().getOrderTotal().equals(getOrderResponse2.getBody().getOrderTotal()));
            System.out.println("Order total changed.");
            assertThat(!getOrderResponse.getBody().getOrderItems().get(0).getQuantity().equals(getOrderResponse2.getBody().getOrderItems().get(0).getQuantity()));
            System.out.println("Order item quantity changed.");
            assertThat(!getOrderResponse.getBody().getOrderItems().get(0).getTotal().equals(getOrderResponse2.getBody().getOrderItems().get(0).getTotal()));
            System.out.println("Order item total changed.");

        }

    }
}