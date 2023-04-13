package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersCustomersTests.readyTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
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
        @Test
        void customerDeleted200() {
//            get valid token
            String token = validToken();
            Assertions.assertNotNull(token);
//            -----------------------------------------------------------------------------
//            delete customer
//        Given: given a valid customer id and auth header
            int customerId = 1;

            HttpHeaders headers2 = new HttpHeaders();
            headers2.setContentType(MediaType.APPLICATION_JSON);
            headers2.setBearerAuth(token);
            HttpEntity<String> headersEntity = new HttpEntity<>(headers2);

            //        When: a connection is made
            String uri2=
                    String.format("%s/%d", getBaseUriForDeleteCustomer(), customerId);
            System.out.println(uri2);



            ResponseEntity<OwnersGetOrderDto> response2 =
                    getRestTemplate().exchange(uri2, HttpMethod.DELETE, headersEntity, new ParameterizedTypeReference<>() {
                    });
//
//        Then: the customer is removed from the database with a 200 response
             Assertions.assertEquals(HttpStatus.OK, response2.getStatusCode());
            System.out.println("Response code is " + response2.getStatusCode() + ".");

//             verify customer deleted
//        And: an attempt to call the order deleted will give a 404 not found response if header holds valid jwt
            String parameter = "customerId";
            String getCustomerUri =
                    String.format("%s?%s=%d", getBaseUriForGetCustomerById(), parameter, customerId);
            System.out.println(getCustomerUri);
            ResponseEntity<OwnersGetCustomerDto> getCustomerResponse =
                    getRestTemplate().exchange
            (getCustomerUri, HttpMethod.GET, headersEntity, new ParameterizedTypeReference<>() {});
            Assertions.assertEquals(HttpStatus.NOT_FOUND, getCustomerResponse.getStatusCode());
            System.out.println("Response code is " + getCustomerResponse.getStatusCode() + ".");
            System.out.println("Customer has been deleted and can not be found.");
        }
    }
}
