package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

public class OrderItemTestSupport extends BaseResponseTest {
    protected String createValidOrderItemBody(){
        return """
                {
                 "itemId" : 3,
                 "cartUuid" : "45644-65325-46654",
                 "quantity" : 2,
                 "total" : 5.50
                }""";
    }

    protected String createInvalidOrderItemBody() {
//        change these values to determine what fails and passes

        return "{\n"
                + " \"id\" : 15,\n"
                + " \"cartUuid\" : \"45#4f12-65325-46654\",\n"
                + " \"quantity\" : 2,\n"
                + " \"total\" : 5.232\n"
                + "}";
    }

}
