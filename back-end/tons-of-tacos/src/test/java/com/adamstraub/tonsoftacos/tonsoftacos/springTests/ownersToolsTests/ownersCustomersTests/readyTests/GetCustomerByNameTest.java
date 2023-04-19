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
                //            get valid token
//                String token = validToken();
                String token = encryptedToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------
//            Given: a valid uid and valid auth header
                String parameter = "name";
                String customerName = "Tim Timson";

                HttpHeaders headers2 = new HttpHeaders();
                headers2.setContentType(MediaType.APPLICATION_JSON);
                headers2.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(headers2);
//            When: a connection is made
                String uri=
                        String.format("%s?%s=%s", getBaseUriForGetCustomerByName(), parameter, customerName );
                System.out.println(uri);
                ResponseEntity<OwnersGetCustomerDto> response =
                        getRestTemplate().exchange(uri, HttpMethod.GET, headersEntity, new ParameterizedTypeReference<>() {
                        });
                System.out.println("Customer returned from db: " + Objects.requireNonNull(response.getBody()).getName());
//            Then: a customer is returned with a name matching the name queried and a 200 response code
                Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
                System.out.println("Response code is " + response.getStatusCode() + ".");
                Assertions.assertEquals(customerName, Objects.requireNonNull(response.getBody()).getName());
                System.out.println("Customer name matches customer name queried: " + response.getBody().getName());
                Assertions.assertEquals(customerName, Objects.requireNonNull(response.getBody()).getName());

            }
        }
}
