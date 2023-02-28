package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

public class OrderItemTestSupport extends BaseResponseTest {
    protected String createValidOrderItemBody(){
        return "{\n"
                + " \"id\" : 3,\n"
                + " \"orderUuid\" : \"45644-65325-46654\",\n"
                + " \"quantity\" : 2,\n"
                + " \"total\" : 5.50\n"
                + "}";
    }

    protected String createInvalidOrderItemBody() {
//        change these values to determine what fails and passes

        return "{\n"
                + " \"id\" : 15,\n"
                + " \"orderUuid\" : \"45#4f12-65325-46654\",\n"
                + " \"quantity\" : 2,\n"
                + " \"total\" : 5.232\n"
                + "}";
    }

}
