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

public class GetCustomerByIdTest {

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
            void getCustomerById200() {
                //            get valid token
                String token = validToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------
//            Given: a valid customer id and auth header
                int customerId = 1;

                HttpHeaders headers2 = new HttpHeaders();
                headers2.setContentType(MediaType.APPLICATION_JSON);
                headers2.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(headers2);
//            When: a connection is made
                String parameter = "customerId";
                String uri =
                        String.format("%s?%s=%d", getBaseUriForGetCustomerById(), parameter, customerId);
                System.out.println(uri);

                ResponseEntity<OwnersGetCustomerDto> response =
                        getRestTemplate().exchange(uri, HttpMethod.GET, headersEntity, new ParameterizedTypeReference<>() {
                        });
//            Then: a customer is retrieved with a matching id
                System.out.println("Response code is " + response.getStatusCode() + ".");
                Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
                Assertions.assertEquals(customerId, Objects.requireNonNull(response.getBody()).getCustomerId());
                System.out.println("Id of customer retrieved matches id queried.");
            }
        }
    }


