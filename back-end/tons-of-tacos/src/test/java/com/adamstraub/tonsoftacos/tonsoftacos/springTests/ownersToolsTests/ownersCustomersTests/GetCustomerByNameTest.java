package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersCustomersTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GetCustomerByNameTest {

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
            void getCustomerByName200() {
//            Given: a valid uid
                String parameter = "name";
                String customerName = "Tim Timson";
//            When: a connection is made
                String getOrderUri =
                        String.format("%s?%s=%s", getBaseUriForGetCustomerByName(), parameter, customerName );
                System.out.println(getOrderUri);
                System.out.println("Customer returned from db: ");
                ResponseEntity<OwnersGetCustomerDto> response =
                        getRestTemplate().exchange(getOrderUri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
//            Then: an order is returned with a uid matching the test uid and a 200 response code
                System.out.println("Response code is " + response.getStatusCode() + ".");
                System.out.println(response.getBody());
                assertThat(Objects.requireNonNull(response.getBody()).getName().equals(customerName));
            }
        }
}
