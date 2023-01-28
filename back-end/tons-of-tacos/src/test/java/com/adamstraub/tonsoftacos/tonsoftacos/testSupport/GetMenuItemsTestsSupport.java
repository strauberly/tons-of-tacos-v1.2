package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;


public class GetMenuItemsTestsSupport extends BaseResponseTest {

    protected MenuItem sample() {

        return MenuItem.builder()
                 .id(1)
               .category("taco")
               .description("nom nom")
               .item_name("golden pound")
               .item_size("NULL")
               .img_url("TBD")
               .unit_price(2.25)
               .build();

        }
    }


