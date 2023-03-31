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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAllCustomersTest {

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
            void allOrdersReturned200() {
//  Given: a successful connection

                String uri =
                        String.format("%s", getBaseUriForGetAllCustomers());
                ResponseEntity<List<OwnersGetCustomerDto>> response =
                        getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                        });
//  Then: a collection of orders is returned with a closed of 200
                System.out.println(("Response code is " + response.getStatusCode() + "."));
                System.out.println(response.getBody());
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            }
    }
}
