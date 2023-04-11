package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ordersTestsSupport;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.TestUris;

public class OrdersTestsSupport extends TestUris {
    protected String validOrderBody(){
return """
        {
            "customer": {
                "name": "billy billson",
                "email": "billy@bolly.com",
                "phoneNumber": "555.555.5959"
            },
            "order": {
                "orderUid": "223-333-444",
                "orderItems": [
                    {
                        "itemId": {
                            "id": 2
                        },
                        "quantity": 2
                    },
                    {
                        "itemId": {
                            "id": 12
                        },
                        "quantity": 1
                    },
                    {
                        "itemId": {
                            "id": 3
                        },
                        "quantity": 3
                    }
                ]
            }
        }
        
        """;

        //        return "{\n"
//                + "  \"customer\": {\n"
//                + "  \"name\":\"billy billson\",\n"
//                + "  \"email\":\"billy@bolly.com\",\n"
//                + "  \"phoneNumber\":\"555.555.5959\"\n"
//                +  "},\n"
//                + "  \"order\": {\n"
//                + "   \"orderUid\":\"223-44-444\", \n"
//                + "  \"orderItems\": [\n"
//                + "{\n"
//                + "\"itemId\":{\n"
//                + "  \"id\":\"2\"\n"
//                + "},\n"
//                + "  \"quantity\":\"2\"\n"
//                + "},\n"
//
//                + "{\n"
//                + "\"itemId\":{\n"
//                + "  \"id\":\"12\"\n"
//                + "},\n"
//                + "  \"quantity\":\"1\"\n"
//                + "},\n"
//
//                + "{\n"
//                + "\"itemId\":{\n"
//                + "  \"id\":\"3\"\n"
//                + "},\n"
//                + "  \"quantity\":\"3\"\n"
//                + "}\n"
//                + "]\n"
//                + "}\n"
//        + "}";
    }
}
