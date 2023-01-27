package com.adamstraub.tonsoftacos.tonsoftacos;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.GetMenuItemsTestsSupport;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


//@DataJpaTest
class MenuItemEndPointTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    @ActiveProfiles("test")
//    @Sql(scripts = {
//            "classpath:scripts/tonsOfTacos-Schema.sql",
//            "classpath:scripts/tonsOfTacos-Data.sql"},
//            config = @SqlConfig(encoding = "utf-8"))

    class testThatDoesNotPolluteTheApplicationContext extends GetMenuItemsTestsSupport{
    @Test
        public void  testThatAMenuItemIsReturnedWith200(){
        System.out.println(getBaseUriForMenuItemQuery());
//        Given: a valid menu item id
        int itemId= 1;
        String parameter = "id";
        String uri =
//        String.format("%s%s?%s=%d", getBaseUriForMenuItemQuery(),parameter,parameter,itemId);
    String.format("%s%d", getBaseUriForMenuItemFastQuery(), itemId);

        System.out.println(uri);

        ResponseEntity<MenuItem> response =
                getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody().getId() + " "
                + response.getBody().getItem_name() + " "
                + response.getBody().getUnit_price() + " "
        );
        Integer actual = response.getBody().getId();
        Object expected = sample().getId();
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    }


}
