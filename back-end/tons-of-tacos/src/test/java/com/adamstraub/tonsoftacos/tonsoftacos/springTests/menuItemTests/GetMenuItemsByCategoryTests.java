package com.adamstraub.tonsoftacos.tonsoftacos.springTests.menuItemTests;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.menuItemTestsSupport.GetMenuItemsTestsSupport;
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
import static org.assertj.core.api.Assertions.assertThat;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;




class GetMenuItemsByCategoryTests {

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
        void menuItemsAreReturnedByCategoryWith200() {
            System.out.println(getBaseUriForMenuItemByCategoryQuery());
//        Given: a valid menu id category
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
            System.out.println(("Response code is " + response.getStatusCode() + "."));
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//           And: returned items category = category entered for query
            LinkedList<MenuItem> returnedCategoryItems = new LinkedList<>(Objects.requireNonNull(response.getBody()));
            assertThat(returnedCategoryItems.get(0).getCategory().equals(categoryName));
            System.out.println("Returned category = " + returnedCategoryItems.get(0).getCategory() + ". Queried " +
                    "category = " + categoryName);
        }

            @Test
            void badCategoryEntryReturns400(){
//        Given: a category that does not exist
                String badInput = "!#%$^";
                String parameter = "category";
                String uri =
                String.format("%s?%s=%s", getBaseUriForMenuItemByCategoryQuery(), parameter, badInput);
//          When: aconnection is made
//                needed map as we hit the error handler which is looking for a map
                ResponseEntity<Map<String, Object>> response =
                        getRestTemplate().exchange(uri, HttpMethod.GET, null,
                                new ParameterizedTypeReference<>() {
                        });
                System.out.println(uri);
                assertThat(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
                System.out.println(("Response code is " + response.getStatusCode() + "."));
            }
    }
}