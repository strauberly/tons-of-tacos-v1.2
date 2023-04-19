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

public class DeleteOrderTests {

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
        void orderDeleted200() {
//        Given: given a valid order id and auth header.

            //            get valid token
//            String token = validToken();
            String token = encryptedToken();
            Assertions.assertNotNull(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);

            int orderId = 1;

            //        When: a connection is made
            String uri =
                    String.format("%s/%d", getBaseUriForDeleteOrder(), orderId);
            System.out.println(uri);

            ResponseEntity<OwnersGetOrderDto> response =
                    getRestTemplate().exchange(uri, HttpMethod.DELETE, headerEntity, new ParameterizedTypeReference<>() {
                    });
//
//        Then: the order is removed from the database with a 200 response
            System.out.println("Response code is " + response.getStatusCode() + ".");
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        And: an attempt to call the order deleted will give a 404 response
            String parameter = "orderId";
            String getOrderUri =
                    String.format("%s?%s=%d", getBaseUriForGetOrderById(), parameter, orderId);
            System.out.println(getOrderUri);
            ResponseEntity<OwnersGetOrderDto> getOrderResponse =
                    getRestTemplate().exchange(getOrderUri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                    });
            System.out.println("Response code is " + getOrderResponse.getStatusCode() + ".");
            System.out.println("Order has been deleted and can not be found.");
            Assertions.assertEquals(HttpStatus.NOT_FOUND, getOrderResponse.getStatusCode());
        }
    }
}
