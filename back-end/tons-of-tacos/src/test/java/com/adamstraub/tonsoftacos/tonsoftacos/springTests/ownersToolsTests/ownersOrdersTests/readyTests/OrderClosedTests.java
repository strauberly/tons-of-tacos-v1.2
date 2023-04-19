package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersOrdersTests.readyTests;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
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

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderClosedTests {
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
        @Test
        void orderMarkedClosedWith200() {
//            Given: a valid order id with valid auth header
            int orderId = 1;

            //            get valid token
//            String token = validToken();
            String token = encryptedToken();
            Assertions.assertNotNull(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);
//            When: a connection is made
            String uri =
                    String.format("%s/%d", getBaseUriForCloseOrder(), orderId);
            System.out.println(uri);

            ResponseEntity<OwnersGetOrderDto> response =
                    getRestTemplate().exchange(uri, HttpMethod.PUT, headerEntity, new ParameterizedTypeReference<>() {
                    });
//            Then: order is marked closed and response code is 200
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            System.out.println("Response code is " + response.getStatusCode() + ".");

            //            And: when the order is called the closed is indeed closed
            String parameter = "orderId";
            String getOrderUri =
                    String.format("%s?%s=%d", getBaseUriForGetOrderById(), parameter, orderId);
            System.out.println(getOrderUri);
            ResponseEntity<OwnersGetOrderDto> getOrderResponse =
                    getRestTemplate().exchange(getOrderUri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                    });
            System.out.println(getOrderResponse.getBody());
            Assertions.assertNotEquals("no", Objects.requireNonNull(getOrderResponse.getBody()).getStatus());
            System.out.println("Response code is " + getOrderResponse.getStatusCode() + ".");
            System.out.println("Order is closed : " + Objects.requireNonNull(getOrderResponse.getBody()).getStatus());
        }
    }
}
