package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersOrdersTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
import org.assertj.core.api.Assertions;
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

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetOrderByCustomerTests {
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
    class testThatDoesNotPolluteTheApplicationContext extends OwnersToolsTestsSupport {
        @Test
        void orderReturnedByCustomerName200() {
//            Given: a customer that has already placed an order
            String parameter = "customer";
            String customer = "John Johnson";
//            When: a successful connection is made
            String uri =
                    String.format("%s?%s=%s", getBaseUriForGetOrderByCustomer(), parameter, customer );
            System.out.println(uri);
            System.out.println("Orders returned from db by customer: ");
            ResponseEntity<List<OwnersGetOrderDto>> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });

//            Then: all open orders will be returned belonging to customer matching provided name with a response
//            code of 200
            System.out.println(response.getBody().stream().findAny().get().getName().equals(customer));
            Assertions.assertThat(Objects.requireNonNull(response.getBody()).stream().findAny().get().getName().equals(customer));
            System.out.println("Customer name submitted matches the following orders: ");
            System.out.println(response.getBody().stream().findAny().get().getName().equals(customer));
            System.out.println(response.getBody());
            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            System.out.println(("Response code is " + response.getStatusCode() + "."));
        }
    }
}
