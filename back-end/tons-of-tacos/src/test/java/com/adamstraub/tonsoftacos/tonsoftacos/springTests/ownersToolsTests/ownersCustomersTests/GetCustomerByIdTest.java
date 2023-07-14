package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersCustomersTests;

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

import java.util.Map;

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
//                String token = validToken();
                String token = encryptedToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------
//            Given: a valid customer id and auth header
                int customerId = 1;

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(headers);
//            When: a connection is made
                String parameter = "customerId";
                String uri =
                        String.format("%s?%s=%d", getBaseUriForGetCustomerById(), parameter, customerId);
                System.out.println(uri);
                ResponseEntity<Map<String, Object>> response =
//                ResponseEntity<OwnersGetCustomerDto> response =
                        getRestTemplate().exchange(uri, HttpMethod.GET, headersEntity, new ParameterizedTypeReference<>() {
                        });
//            Then: a customer is retrieved with a matching id
                System.out.println("Response code is " + response.getStatusCode() + ".");
                Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//                Assertions.assertEquals(customerId, Objects.requireNonNull(response.getBody()).getCustomerId());
                System.out.println(response.getBody());
                System.out.println("Id of customer retrieved matches id queried.");
            }
//          throw exception for not found request
            @Test
            void getInvalidCustomerById404() {
                //            get valid token
//                String token = validToken();
                String token = encryptedToken();
                Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------
//            Given: an invalid customer id and valid auth header
                int customerId = 85;

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(headers);
//            When: a connection is made
                String parameter = "customerId";
                String uri =
                        String.format("%s?%s=%d", getBaseUriForGetCustomerById(), parameter, customerId);
                System.out.println(uri);

                ResponseEntity<Map<String, Object>> response =
                        getRestTemplate().exchange(uri, HttpMethod.GET, headersEntity, new ParameterizedTypeReference<>() {
                        });
//            Then: a 404 is returned
                System.out.println("Response code is " + response.getStatusCode() + ".");
                Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

//            And: the error message contains
                Map<String, Object> error = response.getBody();
                assert error != null;
                Assertions.assertEquals(error.get("status code").toString().substring(0,3), HttpStatus.NOT_FOUND.toString().substring(0,3));
                Assertions.assertTrue(error.containsValue("/api/owners-tools/customers/get-customer/customerId"));
                Assertions.assertTrue(error.containsKey("message"));
                Assertions.assertTrue(error.containsKey("timestamp"));
                System.out.println("error message: " + error);
                System.out.println("Error message contains requested documentation and negative test case is" +
                        " complete and successful.");
            }
        }
}


