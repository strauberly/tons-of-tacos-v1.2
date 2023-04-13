package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersCustomersTests.readyTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetCustomerDto;
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
import java.util.Objects;

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
            void allCustomersReturned200() {
                //            get valid token
                String token = validToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------

//  Given: a successful connection and valid headers
                HttpHeaders headers2 = new HttpHeaders();
                headers2.setContentType(MediaType.APPLICATION_JSON);
                headers2.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(headers2);


                String uri =
                        String.format("%s", getBaseUriForGetAllCustomers());
                ResponseEntity<List<OwnersGetCustomerDto>> response =
                        getRestTemplate().exchange(uri, HttpMethod.GET, headersEntity,
                                new ParameterizedTypeReference<>() {});
//  Then: a collection of orders is returned with a response of 200
                System.out.println(("Response code is " + response.getStatusCode() + "."));
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                Assertions.assertEquals(Objects.requireNonNull(response.getBody()).size(),
                        (int) response.getBody().get(response.getBody().size()-1).getCustomerId());
                System.out.println("Last customer in databases id matches size of array created from customers in DB.");
            }
    }
}
