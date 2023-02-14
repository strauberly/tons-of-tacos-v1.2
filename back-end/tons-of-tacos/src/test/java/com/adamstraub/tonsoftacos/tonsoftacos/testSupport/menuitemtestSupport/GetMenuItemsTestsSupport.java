package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.menuitemtestSupport;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.BaseResponseTest;


public class GetMenuItemsTestsSupport extends BaseResponseTest {

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


