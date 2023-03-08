package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.cartItemsTestsSupport;

import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.BaseResponseTest;

public class CartItemTestSupport extends BaseResponseTest {
    protected String createValidOrderItemBody(){
        return """
                {
                 "itemId" : 3,
                 "order" : "45644-65325-46654",
                 "quantity" : 2,
                 "total" : 5.50
                }""";
    }

    protected String createInvalidOrderItemBody() {
//        change these values to determine what fails and passes

        return "{\n"
                + " \"itemId\" : 15,\n"
                + " \"order\" : \"45#4f12-65325-46654\",\n"
                + " \"quantity\" : 2,\n"
                + " \"total\" : 5.232\n"
                + "}";
    }

}
