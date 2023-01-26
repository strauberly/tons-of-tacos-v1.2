package com.adamstraub.tonsoftacos.tonsoftacos;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.GetMenuItemsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GetMenuItemsTests{
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    @Rollback(false)
    public void addMenuItemTest(){
        MenuItem menuItem = new MenuItem( "topping", "nom nom", "steve's house", "queso", "L", 0.25);
        MenuItem savedItem = menuItemRepository.save(menuItem);
        assertNotNull(savedItem);
    }



//    @Test
//    public void getMenuItemByIdTest(){
////      Given: a valid id
//        int id = 1;
////      When: find menu-item by id number is called
//        MenuItem menuItem = (MenuItem) menuItemRepository.id(id);
////      Then: this is equal to calling menuItem.getId() from the entity stored in db
//        assertThat(menuItem.getId()).isEqualTo(id);
//    }












//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    class GetMenuItemsTests extends GetMenuItemsSupport {
//    @Test
//    void testThatMenuItemsAreReturnedByIdNumber() {
//        System.out.println(getBaseUriForMenuItem());
////        Given: a valid menu-item number
//        Long menuItemID = 1L;
//        String uri =
//                String.format("%s?%d", getBaseUriForMenuItem(), menuItemID);
//        System.out.println(uri);
//
//
////        When: connection is made to uri
//        ResponseEntity<MenuItem> response =
//        getRestTemplate().getForEntity(uri, MenuItem.class);
//
//
////        Then: a ok/200 status code is returned
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//    }
}