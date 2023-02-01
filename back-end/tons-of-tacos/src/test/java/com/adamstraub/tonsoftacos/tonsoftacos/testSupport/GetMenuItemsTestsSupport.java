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


