package com.adamstraub.tonsoftacos.tonsoftacos.springTests.menuItemTests;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.menuitemtestSupport.GetMenuItemsTestsSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@DataJpaTest
class GetMenuItemsTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    @ActiveProfiles("test")
//    @Sql(scripts = {
//            "classpath:scripts/tonsOfTacos-Schema.sql",
//            "classpath:scripts/tonsOfTacos-Data.sql"},
//            config = @SqlConfig(encoding = "utf-8"))
    class testThatDoesNotPolluteTheApplicationContext extends GetMenuItemsTestsSupport {
        @Test
         void testThatAMenuItemIsReturnedWith200() {
            System.out.println(getBaseUriForMenuItemQuery());
//        Given: a valid menu item id
            int itemId = 1;
            String parameter = "id";
            String uri =
                    String.format("%s%s?%s=%d", getBaseUriForMenuItemQuery(), parameter, parameter, itemId);
//             Test endpoint for quick item grab
            //    String.format("%s%d", getBaseUriForMenuItemFastQuery(), itemId);

            System.out.println(uri);
//      When: Connection is made
            ResponseEntity<MenuItem> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
//            Then: A 200 status code is returned
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            System.out.println(Objects.requireNonNull(response.getBody()).getMenuItemId() + " "
                    + response.getBody().getItemName() + " "
                    + response.getBody().getUnitPrice() + " "
            );
            //            And: it matches the expected outcome
            Integer actual = response.getBody().getMenuItemId();
            Object expected = sample().getMenuItemId();

            Assertions.assertThat(actual).isEqualTo(expected);
        }
        @Test
         void testThatMenuItemsAreReturnedByCategoryWith200() {
            System.out.println(getBaseUriForMenuItemQuery());
//        Given: a valid menu item category
            String category = "taco";
            String parameter = "category";
            String uri =
                    String.format("%s%s?%s=%s", getBaseUriForMenuItemQuery(), parameter, parameter, category);
            System.out.println(uri);
//         When: when connection is made to uri
            ResponseEntity <Object> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });
//            Then: a 200 status code is returned
            Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }

    }


}

