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

import java.util.List;


public class GetOrdersByCustomerTests {
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
    class testThatDoesNotPolluteTheApplicationContextUris extends OwnersToolsTestsSupport {
        @Test
        void orderReturnedByCustomerName200() {
//            Given: a customer that has already placed an order and a valid auth header

            //            get valid token
            String token = validToken();
            org.junit.jupiter.api.Assertions.assertNotNull(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);

            String parameter = "customer";
            String customer = "John Johnson";
//            When: a successful connection is made
            String uri =
                    String.format("%s?%s=%s", getBaseUriForGetOrderByCustomer(), parameter, customer );
            System.out.println(uri);
            ResponseEntity<List<OwnersGetOrderDto>> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                    });

//            Then: all orders will be returned belonging to customer matching provided name with a response
//            code of 200
            System.out.println(response.getBody().stream().findAny().get().getName().equals(customer));
            Assertions.assertEquals(response.getBody().stream().findAny().get().getName(), customer);
            System.out.println("Customer name submitted: " + customer + " matches the following orders: ");
            System.out.println(response.getBody().stream().findAny().get().getName().equals(customer));
            System.out.println(response.getBody());
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            System.out.println(("Response code is " + response.getStatusCode() + "."));
        }
    }
}
