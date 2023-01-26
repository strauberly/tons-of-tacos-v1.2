package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.data.rest.webmvc.PersistentEntityResource.build;

public class GetMenuItemsTestsSupport extends BaseTest {

    protected MenuItem buildExpected() {
//        MenuItem menuItem = new MenuItem();

        return MenuItem.builder()
                .id(1)
                .category("taco")
                .description("nom nom")
                .item_name("pound")
                .item_size("NULL")
                .img_url("TBD")
                .unit_price(2.25)
                .build();

    }
}

