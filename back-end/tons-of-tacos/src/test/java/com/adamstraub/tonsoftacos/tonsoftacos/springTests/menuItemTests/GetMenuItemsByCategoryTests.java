package com.adamstraub.tonsoftacos.tonsoftacos.springTests.menuItemTests;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.menuitemtestSupport.GetMenuItemsTestsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;


import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class GetMenuItemsByCategoryTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @PropertySource("classpath:application.properties")
    @Sql(scripts = {
            "classpath:/schema.sql",
            "classpath:/data.sql",
    },
            config = @SqlConfig(encoding = "utf-8"))
    static
    class testThatDoesNotPolluteTheApplicationContext extends GetMenuItemsTestsSupport {
        @Test
        void menuItemsAreReturnedByCategoryWith200() {
            System.out.println(getBaseUriForMenuItemByCategoryQuery());
//        Given: a valid menu item category
            String categoryName = "taco";
            String parameter = "category";
            String uri =
                    String.format("%s?%s=%s", getBaseUriForMenuItemByCategoryQuery(), parameter, categoryName);
            System.out.println(uri);


//         When: when connection is made to uri
            ResponseEntity<List<MenuItem>> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
//            Then: a 200 status code is returned
// add each menu Item from response get body and then print item name and category
//            most likely through a stream
//            System.out.println(response.getBody().);
            LinkedList<String> returnedCategoryItems = new LinkedList<>();


            System.out.println(("Response code is " + response.getStatusCode() + "."));
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        }
    }
}