package com.adamstraub.tonsoftacos.tonsoftacos.springTests.menuItemTests;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.menuitemtestSupport.CreateMenuItemTestSupport;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CreateNewMenuItemTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @ActiveProfiles("test")
    @Sql(scripts = {
            "classpath:scripts/tonsOfTacos-Schema.sql",
            "classpath:scripts/tonsOfTacos-Data.sql"},
            config = @SqlConfig(encoding = "utf-8"))

    class testThatDoesNotPolluteTheApplicationContext extends CreateMenuItemTestSupport {
        @Test

        void createAMenuItemWith201Response(){
    String body = createMenuItemBody();
    String uri = getBaseUriForMenuItem();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
    ResponseEntity<MenuItem> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, MenuItem.class);

   assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        }
    }


}
