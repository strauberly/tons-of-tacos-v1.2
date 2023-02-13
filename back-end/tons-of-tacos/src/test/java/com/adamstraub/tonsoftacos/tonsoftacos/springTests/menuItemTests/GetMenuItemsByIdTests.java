package com.adamstraub.tonsoftacos.tonsoftacos.springTests.menuItemTests;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.menuitemtestSupport.GetMenuItemsTestsSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GetMenuItemsByIdTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @PropertySource("classpath:application.properties")
    @Sql(scripts = {
            "classpath:/schema.sql",
            "classpath:/data.sql",
            },
            config = @SqlConfig(encoding = "utf-8"))
    class testThatDoesNotPolluteTheApplicationContext extends GetMenuItemsTestsSupport {
        @Test
         void testThatAMenuItemIsReturnedByIdWith200() {
            System.out.println(getBaseUriForMenuItemByIdQuery());
//        Given: a valid menu item id
            int itemId = 1;
            String parameter = "id";
            String uri =
                    String.format("%s%s?%s=%d", getBaseUriForMenuItemByIdQuery(), parameter, parameter, itemId);
            System.out.println(uri);
//      When: Connection is made
            ResponseEntity<MenuItem> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
//            Then: A 200 status code is returned
            System.out.println("Response code is " + response.getStatusCode() + ".");
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            //            And: it matches the expected outcome
            String actual = String.valueOf(Objects.requireNonNull(response.getBody()).getId());
            String expected = String.valueOf(sample().getId());
            System.out.println("Actual item id returned is " + actual + ", and expected item id is " + expected + ".");
            Assertions.assertThat(actual).isEqualTo(expected);
        }


    }


}

