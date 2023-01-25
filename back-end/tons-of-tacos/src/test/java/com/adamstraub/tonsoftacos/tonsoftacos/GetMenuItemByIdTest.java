package com.adamstraub.tonsoftacos.tonsoftacos;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.GetMenuItemByTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class GetMenuItemByIdTest extends GetMenuItemByTestSupport {
    @Test
    void testThatMenuItemsAreReturnedByIdNumber() {
        System.out.println(getBaseUriForMenuItem());
//        Given: a valid menu-item number
        Long menuItemID = 1L;
        String uri =
                String.format("%s?%d", getBaseUriForMenuItem(), menuItemID);
        System.out.println(uri);


//        When: connection is made to uri
        ResponseEntity<MenuItem> response =
        getRestTemplate().getForEntity(uri, MenuItem.class);


//        Then: a ok/200 status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}