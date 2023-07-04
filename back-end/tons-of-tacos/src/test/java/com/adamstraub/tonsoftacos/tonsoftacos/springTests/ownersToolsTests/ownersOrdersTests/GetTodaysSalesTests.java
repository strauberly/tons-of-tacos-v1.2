package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersOrdersTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetTodaysSalesTests {
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
        @Autowired
        OrdersRepository ordersRepository;
        @Test
        void todaysSalesReturned200() {

//            Given: one or more orders have been closed marking a sale and a valid authheader
            //            get valid token
//            String token = validToken();
            String token = encryptedToken();
            Assertions.assertNotNull(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);

//            mark order ready
            int orderId = 1;
            String uri =
                    String.format("%s/%d", getBaseUriForOrderReady(), orderId);
            System.out.println(uri);

            ResponseEntity<OwnersGetOrderDto> orderReadyResponse =
                    getRestTemplate().exchange(uri, HttpMethod.PUT, headerEntity, new ParameterizedTypeReference<>() {
                    });

            System.out.println(orderReadyResponse.getStatusCode());
            System.out.println(orderReadyResponse.getBody());
            System.out.println("Order ready: " + (!ordersRepository.getById(orderId).getReady().equals("no")));

//            mark order closed
            String closeOrderUri =
                    String.format("%s/%d", getBaseUriForCloseOrder(), orderId);
            System.out.println(closeOrderUri);

            ResponseEntity<OwnersGetOrderDto> orderClosedResponse =
                    getRestTemplate().exchange(closeOrderUri, HttpMethod.PUT, headerEntity, new ParameterizedTypeReference<>() {
                    });
            System.out.println(orderClosedResponse.getStatusCode());
            System.out.println(orderClosedResponse.getBody());
            System.out.println( "Order closed: " + !ordersRepository.getById(orderId).getClosed().equals("no"));


//            When:  a successful connection made
            String salesUri =
                    String.format("%s", getBaseUriForSales());
            System.out.println(salesUri);

            ResponseEntity<String> response =
                    getRestTemplate().exchange(salesUri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                    });
//            Then:  response code will be 200
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            System.out.println("Response code is " + response.getStatusCode() + ".");
            System.out.println(response.getBody());
//            And:   console print out should match sum totals of orders closed
            AtomicReference<Double> summedClosedOrderTotals = new AtomicReference<>(0.00);
            List<Orders> orders = ordersRepository.findAll();
            List<Orders> closedOrders = new ArrayList<>();
            for (Orders order: orders){
                if (!order.getClosed().equals("no")){
                    closedOrders.add(order);
                }
            }
            closedOrders.forEach(closedOrder -> summedClosedOrderTotals.updateAndGet(v -> v + closedOrder.getOrderTotal()));
            System.out.println("Summed total for orders marked closed = " + summedClosedOrderTotals);
        }
    }

}
