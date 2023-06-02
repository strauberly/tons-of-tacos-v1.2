package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersCustomersTests;

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

import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteCustomerByIdTest {
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

//        test for bad / expired jwt
//        test for invalid customer id


        @Test
        void customerDeleted200() {
            //        Given: given a valid customer id and auth header
//            get valid token
//            String token = validToken();
            String token = encryptedToken();
//            Assertions.assertNotNull(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);
            System.out.println(headerEntity);
            int customerId = 1;


            //        When: a connection is made
            String uri=
                    String.format("%s/%d", getBaseUriForDeleteCustomer(), customerId);
            System.out.println(uri);


            ResponseEntity<OwnersGetOrderDto> response =
                    getRestTemplate().exchange(uri, HttpMethod.DELETE, headerEntity, new ParameterizedTypeReference<>() {
                    });
//
//        Then: the customer is removed from the database with a 200 response
             Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            System.out.println("Response code is " + response.getStatusCode() + ".");

//             verify customer deleted

//        And: an attempt to call the order deleted will give a 404
            String parameter = "customerId";
            String getCustomerUri =
                    String.format("%s?%s=%d", getBaseUriForGetCustomerById(), parameter, customerId);
            System.out.println(getCustomerUri);
            ResponseEntity<OwnersGetCustomerDto> getCustomerResponse =
                    getRestTemplate().exchange
            (getCustomerUri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {});
            Assertions.assertEquals(HttpStatus.NOT_FOUND, getCustomerResponse.getStatusCode());
            System.out.println("Response code is " + getCustomerResponse.getStatusCode() + ".");
            System.out.println("Customer has been deleted and can not be found.");
        }
        @Test
        void operationForbiddenBadToken403() {
//            Given: an invalid bearer auth token
            String token = expiredToken();
            Assertions.assertNotNull(token);
            System.out.println(token);

//           build authheader
            HttpHeaders authHeader = new HttpHeaders();
            authHeader.setContentType(MediaType.APPLICATION_JSON);
            authHeader.setBearerAuth(token);
            HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);
            System.out.println(headerEntity);
            int customerId = 1;


            //        When: a connection is made
            String uri=
                    String.format("%s/%d", getBaseUriForDeleteCustomer(), customerId);
            System.out.println(uri);


            ResponseEntity<Map<String, Object>> response =
                    getRestTemplate().exchange(uri, HttpMethod.DELETE, headerEntity, new ParameterizedTypeReference<>() {
                    });
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response);
//



        }
    }
}
