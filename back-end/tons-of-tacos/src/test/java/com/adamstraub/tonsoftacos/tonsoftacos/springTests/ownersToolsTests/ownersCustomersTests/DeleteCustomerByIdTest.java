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
//        Given: given a valid customer id
            int customerId = 1;
//        When: a connection is made
            String uri =
                    String.format("%s/%d", getBaseUriForDeleteCustomer(), customerId);
            System.out.println(uri);

            ResponseEntity<OwnersGetOrderDto> response =
                    getRestTemplate().exchange(uri, HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
                    });
//
//        Then: the custoemr is removed from the database with a 200 response
            System.out.println("Response code is " + response.getStatusCode() + ".");
            assertThat(response.getStatusCode().equals(HttpStatus.OK));
//        And: an attempt to call the order deleted will give a 404 response
            String parameter = "customerId";
            String getCustomerUri =
                    String.format("%s?%s=%d", getBaseUriForGetCustomerById(), parameter, customerId);
            System.out.println(getCustomerUri);
            ResponseEntity<OwnersGetCustomerDto> getCustomerResponse =
                    getRestTemplate().exchange(getCustomerUri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
            System.out.println("Response code is " + getCustomerResponse.getStatusCode() + ".");
            System.out.println("Customer has been deleted and can not be found.");
            assertThat(getCustomerResponse.getStatusCode().equals(HttpStatus.NOT_FOUND));
        }
    }
}
