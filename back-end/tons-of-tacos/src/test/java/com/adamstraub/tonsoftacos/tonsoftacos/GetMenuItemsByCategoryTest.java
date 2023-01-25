package com.adamstraub.tonsoftacos.tonsoftacos;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.GetMenuItemByCategoryTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetMenuItemsByCategoryTest extends GetMenuItemByCategoryTestSupport {

    @Test
    void testThatMenuItemsAreReturnedByCategory() {
        System.out.println(getBaseUriForMenuItem());
//        Given: a valid menu-item category
        String menuItemCategory = "milk";

        String uri =
                String.format("%s?%s", getBaseUriForMenuItem(), menuItemCategory);
        System.out.println(uri);

//        When: connection is made to uri
        ResponseEntity<MenuItem> response =
                getRestTemplate().getForEntity(uri, MenuItem.class);

//        Then: a ok/200 status code is returned
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}