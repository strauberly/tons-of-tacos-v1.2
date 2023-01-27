package com.adamstraub.tonsoftacos.tonsoftacos;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.GetMenuItemsTestsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GetMenuItemsTests extends GetMenuItemsTestsSupport {
//    ** These are just in memory database tests
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
//    @Rollback(false)
    public void addMenuItemTestToDB(){
//        try builder here
//        MenuItem menuItem = new MenuItem( "taco", "nom nom", "steve's house", "super taco", null, 3.50);
//        MenuItem savedItem = menuItemRepository.save(menuItem);
//        assertNotNull(savedItem);
    }



}