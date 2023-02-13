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
//        VALUES('taco', 'nom nom', 'pound', NULL, 'TBD', 2.25);
        }

    protected String createMenuItemBody(){
        return "{\n" +
                " \"category\" : \"taco\", \n" +
                " \"description\" : \"nom-nom\", \n" +
                " \"item_name\" : \"ton\", \n" +
                " \"item_size\" : \"NULL\", \n" +
                " \"img_url\" : \"TBD\", \n" +
                " \"unit_price\" : \"2.25\", \n" +
                "}";
    }
    }


