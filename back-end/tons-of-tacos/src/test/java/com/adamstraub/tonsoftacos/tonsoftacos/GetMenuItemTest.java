package com.adamstraub.tonsoftacos.tonsoftacos;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.GetMenuItemTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    class GetMenuItemTest extends GetMenuItemTestSupport {
    @Test
    void testThatDBItemsAreReturnedByIdNumber200() {
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


    @Test
    void testThatOrderIsCreated201() {

//        Given: valid menu items have been stored in a cart

//        When: connection is made to uri

//        Then: a ok/201 status code is returned and an order is created in db table
    }

    @Test
    void testThatOrderIsRetrievableByOrderNumberOrName200() {

//        Given: valid order number or customer name is provided

//        When: connection is made to uri

//        Then: a ok/200 status code is returned and a order is retrieved

    }
}