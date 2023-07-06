package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersOrdersTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersDailySalesDto;
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
import java.util.Map;
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

            int orderIdOne = 1;
            int orderIdTwo = 2;

//            mark order one ready

            String orderOneReadyUri =
                    String.format("%s/%d", getBaseUriForOrderReady(), orderIdOne);
            System.out.println(orderOneReadyUri);

            ResponseEntity<OwnersGetOrderDto> orderOneReadyResponse =
                    getRestTemplate().exchange(orderOneReadyUri, HttpMethod.PUT, headerEntity, new ParameterizedTypeReference<>() {
                    });

            System.out.println(orderOneReadyResponse.getStatusCode());
            System.out.println(orderOneReadyResponse.getBody());
            System.out.println("Order one ready: " + (!ordersRepository.getById(orderIdOne).getReady().equals("no")));

//            mark order one closed
            String closeOrderOneUri =
                    String.format("%s/%d", getBaseUriForCloseOrder(), orderIdOne);
            System.out.println(closeOrderOneUri);

            ResponseEntity<OwnersGetOrderDto> orderOneClosedResponse =
                    getRestTemplate().exchange(closeOrderOneUri, HttpMethod.PUT, headerEntity, new ParameterizedTypeReference<>() {
                    });
            System.out.println(orderOneClosedResponse.getStatusCode());
            System.out.println(orderOneClosedResponse.getBody());
            System.out.println( "Order one closed: " + !ordersRepository.getById(orderIdOne).getClosed().equals("no"));

            //            mark order two ready

            String orderTwoReadyUri =
                    String.format("%s/%d", getBaseUriForOrderReady(), orderIdTwo);
            System.out.println(orderOneReadyUri);

            ResponseEntity<OwnersGetOrderDto> orderTwoReadyResponse =
                    getRestTemplate().exchange(orderTwoReadyUri, HttpMethod.PUT, headerEntity, new ParameterizedTypeReference<>() {
                    });

            System.out.println(orderTwoReadyResponse.getStatusCode());
            System.out.println(orderTwoReadyResponse.getBody());
            System.out.println("Order two ready: " + (!ordersRepository.getById(orderIdOne).getReady().equals("no")));


            //            mark order two closed
            String closeOrderTwoUri =
                    String.format("%s/%d", getBaseUriForCloseOrder(), orderIdTwo);
            System.out.println(closeOrderTwoUri);

            ResponseEntity<OwnersGetOrderDto> orderTwoClosedResponse =
                    getRestTemplate().exchange(closeOrderTwoUri, HttpMethod.PUT, headerEntity, new ParameterizedTypeReference<>() {
                    });
            System.out.println(orderTwoClosedResponse.getStatusCode());
            System.out.println(orderTwoClosedResponse.getBody());
            System.out.println( "Order two closed: " + !ordersRepository.getById(orderIdOne).getClosed().equals("no"));




//            When:  a successful connection made
            String salesUri =
                    String.format("%s", getBaseUriForSales());
            System.out.println(salesUri);

            ResponseEntity<Map<String, Object>> response =
                    getRestTemplate().exchange(salesUri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                    });
//            Then:  response code will be 200
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            System.out.println("Response code is " + response.getStatusCode() + ".");
            System.out.println(response.getBody());

//            And: the sum of the orders totals will equal the daily sales total
            Assertions.assertEquals(Objects.requireNonNull(orderOneReadyResponse.getBody()).getOrderTotal() + Objects.requireNonNull(orderTwoReadyResponse.getBody()).getOrderTotal(), Objects.requireNonNull(response.getBody()).get("total"));
            System.out.println("Total of order 1 and order 2 equals daily sales total: " + (orderOneReadyResponse.getBody().getOrderTotal() + orderTwoReadyResponse.getBody().getOrderTotal() == (double) response.getBody().get("total")));
            System.out.println("Successful test case for daily sales complete.");
////            And:   console print out should match sum totals of orders closed
//            AtomicReference<Double> summedClosedOrderTotals = new AtomicReference<>(0.00);
//            List<Orders> orders = ordersRepository.findAll();
//            List<Orders> closedOrders = new ArrayList<>();
//            for (Orders order: orders){
//                if (!order.getClosed().equals("no")){
//                    closedOrders.add(order);
//                }
//            }
//            closedOrders.forEach(closedOrder -> summedClosedOrderTotals.updateAndGet(v -> v + closedOrder.getOrderTotal()));
//            System.out.println("Summed total for orders marked closed = " + summedClosedOrderTotals);
        }
    }

}
