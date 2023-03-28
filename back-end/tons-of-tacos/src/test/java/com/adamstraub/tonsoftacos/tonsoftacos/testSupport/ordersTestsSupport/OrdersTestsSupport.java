package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ordersTestsSupport;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.BaseResponseTest;

public class OrdersTestsSupport extends BaseResponseTest {
    protected String validOrderBody(){
        return "{\n"
                + "  \"customer\": {\n"
                + "  \"name\":\"billy billson\",\n"
                + "  \"email\":\"billy@bolly.com\",\n"
                + "  \"phoneNumber\":\"555.555.5959\"\n"
                +  "},\n"
                + "  \"order\": {\n"
                + "   \"orderUid\":\"223-44-444\", \n"
                + "  \"orderItems\": [\n"
                + "{\n"
                + "\"itemId\":{\n"
                + "  \"id\":\"2\"\n"
                + "},\n"
                + "  \"quantity\":\"2\"\n"
                + "},\n"

                + "{\n"
                + "\"itemId\":{\n"
                + "  \"id\":\"12\"\n"
                + "},\n"
                + "  \"quantity\":\"1\"\n"
                + "},\n"

                + "{\n"
                + "\"itemId\":{\n"
                + "  \"id\":\"3\"\n"
                + "},\n"
                + "  \"quantity\":\"3\"\n"
                + "}\n"
                + "]\n"
                + "}\n"
        + "}";
    }
}
