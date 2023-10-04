package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersCustomersTests;

import com.adamstraub.tonsoftacos.dto.businessDto.CustomerReturnedToOwner;
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
import java.util.Map;
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
//  Given: a valid auth header
                String token = encryptedToken();
                System.out.println(token);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setBearerAuth(token);
                HttpEntity<String> headersEntity = new HttpEntity<>(headers);

// When: successful connection is made to the end point

                String uri =
                        String.format("%s", getBaseUriForGetAllCustomers());
                ResponseEntity<List<CustomerReturnedToOwner>> response =
                        getRestTemplate().exchange(uri, HttpMethod.GET, headersEntity,
                                new ParameterizedTypeReference<>() {});
//  Then: a collection of customers is returned with a response of 200
                System.out.println(("Response code is " + response.getStatusCode() + "."));
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
////  And:  the size of the array of customers matches the id of the last customer
//                int numberOfCustomers = Objects.requireNonNull(response.getBody()).size();
//                Assertions.assertEquals(Objects.requireNonNull(response.getBody()).size(),
//                        (int) response.getBody().get(response.getBody().size()-1).getCustomerId());
                System.out.println("Response: " + response.getBody());
//                System.out.println("All customers found: " + (numberOfCustomers == response.getBody().get(response.getBody().size() - 1).getCustomerId()));
                System.out.println("Successful test case complete.");
            }
// must comment out sections of test db before this test in order for it to operate as intended.
// Try this test with Mockito.
//    @Test
//    void noCustomersReturned404() {
////  Given: a valid auth header
//        String token = encryptedToken();
//        System.out.println(token);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(token);
//        HttpEntity<String> headersEntity = new HttpEntity<>(headers);
//
//// When: successful connection is made to the end point and no customers are found
//        String uri =
//                String.format("%s", getBaseUriForGetAllCustomers());
//        ResponseEntity<Map<String, Object>> response =
////        ResponseEntity<List<Map<String, Object>>> response =
//        //        ResponseEntity<List<OwnersGetCustomerDto>> response =
//                getRestTemplate().exchange(uri, HttpMethod.GET, headersEntity,
//                        new ParameterizedTypeReference<>() {});
//        System.out.println(response.getStatusCode());
//        System.out.println(response.getBody());
////  Then: a collection of orders is returned with a response of 200
//        System.out.println(("Response code is " + response.getStatusCode() + "."));
////        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
////  And:  the size of the array of customers matches the id of the last customer
////        int numberOfCustomers = Objects.requireNonNull(response.getBody()).size();
////        Assertions.assertEquals(Objects.requireNonNull(response.getBody()).size(),
////                (int) response.getBody().get(response.getBody().size()-1).getCustomerId());
////        System.out.println("Response body is: " + response.getBody());
////        System.out.println("All customers found: " + (numberOfCustomers == response.getBody().get(response.getBody().size() - 1).getCustomerId()));
////        System.out.println("Successful test case complete.");
//    }
}
}
