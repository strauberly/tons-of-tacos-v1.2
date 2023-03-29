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

public class OrderReadyTests {

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
            void orderMarkedReadyWith200() {
//            Given: a valid order id
                int orderId = 1;
//            When: a connection is made
                String uri =
                        String.format("%s/%d", getBaseUriForOrderReady(), orderId);
                System.out.println(uri);

                ResponseEntity<OwnersGetOrderDto> response =
                        getRestTemplate().exchange(uri, HttpMethod.PATCH, null, new ParameterizedTypeReference<>() {
                        });
//            Then: order is marked ready and response code is 200

                assertThat(response.getStatusCode().equals(HttpStatus.OK));
                System.out.println("Response code is " + response.getStatusCode() + ".");
//            And: when the order is called ready does not == no
                String parameter = "orderId";
                String getOrderUri =
                        String.format("%s?%s=%d", getBaseUriForGetOrderById(), parameter, orderId);
                System.out.println(getOrderUri);
                ResponseEntity<OwnersGetOrderDto> getOrderResponse =
                        getRestTemplate().exchange(getOrderUri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
                System.out.println("Order ready as of: " + Objects.requireNonNull(getOrderResponse.getBody()).getReady());
                assertThat(!getOrderResponse.getBody().getReady().equals("no"));
            }
        }
}

