package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ordersTestsSupport;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.BaseResponseTest;

public class OrdersTestsSupport extends BaseResponseTest {
    protected String validOrderBody(){
        return "{\n"
                + "  \"customerId\":\"3\",\n"
                + "  \"orderTotal\":\"62.45\",\n"
                + "  \"orderUid\":\"32219-5465-654\",\n"
                + "  \"orderItems\": [\n"
                + "{\n"
                + "\"itemId\":{\n"
                + "  \"id\":\"2\"\n"
                + "},\n"
                + "  \"quantity\":\"2\",\n"
                + "  \"total\":\"6.00\" \n"
                + "}\n"
                + "]\n"
                + "}";

//        {
//            "customerId": 3,
//                "orderTotal" : 22.50,
//                "orderUid" : "222-4344-444",
//                "orderItems": [
//            {
//                "itemId": {
//                "id": 1
//            },
//                "quantity": 3,
//                    "total": 3.0
//            },
//            {
//                "itemId": {
//                "id": 2
//            },
//                "quantity": 8,
//                    "total": 45.00
//            }
//    ]
//        }

    }
}
