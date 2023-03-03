package com.adamstraub.tonsoftacos.tonsoftacos.springTests.menuItemTests;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.menuitemtestSupport.GetMenuItemsTestsSupport;
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

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GetMenuItemsByIdTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @TestPropertySource("classpath:application.properties")
    @Sql(scripts = {
            "classpath:/test-schema.sql",
            "classpath:/test-data.sql",
            },
            config = @SqlConfig(encoding = "utf-8"))
    class testThatDoesNotPolluteTheApplicationContext extends GetMenuItemsTestsSupport {
        @Test
         void validMenuItemIsReturnedByIdWith200() {
            System.out.println(getBaseUriForMenuItemByIdQuery());
//        Given: a valid menu id id
            int itemId = 1;


            //      When: Connection is made
            String parameter = "id";
            String uri =
                    String.format("%s?%s=%d", getBaseUriForMenuItemByIdQuery(), parameter, itemId);
            System.out.println(uri);

            ResponseEntity<MenuItem> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
//            Then: A 200 status code is returned
            System.out.println("Response code is " + response.getStatusCode() + ".");
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            //            And: it matches the expected outcome
            String actual = String.valueOf(Objects.requireNonNull(response.getBody()).getId());
            String expected = String.valueOf(sample().getId());
            System.out.println("Actual id id returned is " + actual + ", and expected id id is " + expected + ".");
            assertThat(actual).isEqualTo(expected);
        }


        @Test
        void badRequestReturns400() {
            System.out.println(getBaseUriForMenuItemByIdQuery());
//        Given: an invalid menu id id
            String badInput = "!#%$^";
            String parameter = "id";
            String uri =
                    String.format("%s?%s=%s", getBaseUriForMenuItemByIdQuery(), parameter, badInput);
            System.out.println(uri);
//      When: Connection is made
            ResponseEntity<MenuItem> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
//            Then: A 400 status code is returned
            System.out.println("Response code is " + response.getStatusCode() + ".");
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        }

        @Test
        void nonExistentMenuItemReturns404() {
            System.out.println(getBaseUriForMenuItemByIdQuery());
//        Given: an invalid menu id id
            int itemId = 45;
            String parameter = "id";
            String uri =
                    String.format("%s?%s=%d", getBaseUriForMenuItemByIdQuery(), parameter, itemId);
            System.out.println(uri);
//      When: Connection is made
            ResponseEntity<MenuItem> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
//            Then: A 404 status code is returned
            System.out.println("Response code is " + response.getStatusCode() + ".");
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
    }
}

