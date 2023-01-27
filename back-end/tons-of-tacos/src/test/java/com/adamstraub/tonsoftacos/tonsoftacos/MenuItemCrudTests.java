package com.adamstraub.tonsoftacos.tonsoftacos;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.MenuItemCrudTestsSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MenuItemCrudTests extends MenuItemCrudTestsSupport {
//    ** CRUD TEST TO BE MIGRATED
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
//    @Rollback(false)
    void addMenuItemTestToDB(){
//        try builder here
//        MenuItem menuItem = new MenuItem( "taco", "nom nom", "steve's house", "super taco", null, 3.50);

        MenuItem savedItem = menuItemRepository.save(sample());
        assertNotNull(savedItem);
    }

    @Test
    void getItemFromDatabase(){

    }

}