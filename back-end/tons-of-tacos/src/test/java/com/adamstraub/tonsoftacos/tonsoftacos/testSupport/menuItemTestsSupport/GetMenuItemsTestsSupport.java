package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.menuItemTestsSupport;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.TestUris;


public class GetMenuItemsTestsSupport extends TestUris {

    protected MenuItem sample() {

        return MenuItem.builder()
                 .id(1)
               .category("taco")
               .description("nom nom")
               .itemName("pound")
               .itemSize("NULL")
               .imgUrl("TBD")
               .unitPrice(2.25)
               .build();
        }
    }


