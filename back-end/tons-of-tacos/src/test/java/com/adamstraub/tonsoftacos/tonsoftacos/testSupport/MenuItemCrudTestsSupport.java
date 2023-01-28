package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;

public class MenuItemCrudTestsSupport {
    protected MenuItem sample() {
//        MenuItem menuItem = new MenuItem();

        return MenuItem.builder()
//                .id(27)
                .category("taco")
                .description("nom nom")
                .item_name("hot")
                .item_size("NULL")
                .img_url("TBD")
                .unit_price(2.25)
                .build();
    }
}
