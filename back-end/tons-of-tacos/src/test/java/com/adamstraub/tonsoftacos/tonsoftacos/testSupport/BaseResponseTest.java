package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.bind.annotation.PatchMapping;


public class BaseResponseTest {

    @Value("${local.server.port}")
    private String serverPort;


    @Autowired
    @Getter
    private TestRestTemplate restTemplate;

//============== menu uris ====================//
        protected String getBaseUriForMenuItemByIdQuery(){
            return String.format("http://localhost:%d/api/menu/id", Integer.valueOf(serverPort));

        }
    protected String getBaseUriForMenuItemByCategoryQuery(){
        return String.format("http://localhost:%d/api/menu/category", Integer.valueOf(serverPort));

    }

    //    ============== orders uris ====================//

    protected  String getBaseUriForCreateOrder(){
        return String.format("http://localhost:%d/api/order/checkout", Integer.valueOf(serverPort));
    }

    //    ============== owners-tools uris ====================//

    protected  String getBaseUriForGetAllOrders(){
        return String.format("http://localhost:%d/api/owners-tools/get-orders", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForGetOrderByUid(){
        return String.format("http://localhost:%d/api/owners-tools/get-order/orderUid",
                Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForGetOrderById(){
        return String.format("http://localhost:%d/api/owners-tools/get-order/orderId",
                Integer.valueOf(serverPort));
    }


    protected  String getBaseUriForGetOrderByCustomer(){
        return String.format("http://localhost:%d/api/owners-tools/get-order/customer", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForOrderReady(){
        return String.format("http://localhost:%d/api/owners-tools/order-ready", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForCloseOrder(){
        return String.format("http://localhost:%d/api/owners-tools/close-order", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForDeleteOrder(){
        return String.format("http://localhost:%d/api/owners-tools/delete-order", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForEditOrderItem(){
        return String.format("http://localhost:%d/api/owners-tools/update-order-item", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForAddOrderItem(){
        return String.format("http://localhost:%d/api/owners-tools/add-to-order", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForSales(){
        return String.format("http://localhost:%d/api/owners-tools/sales", Integer.valueOf(serverPort));
    }

}
