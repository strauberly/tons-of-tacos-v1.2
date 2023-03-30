package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;


public class TestUris {

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

    //============== order uris ====================//

    protected  String getBaseUriForCreateOrder(){
        return String.format("http://localhost:%d/api/order/checkout", Integer.valueOf(serverPort));
    }

    //============== owners-tools order functions uris ====================//

    protected  String getBaseUriForGetAllOrders(){
        return String.format("http://localhost:%d/api/owners-tools/orders/get-orders", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForGetOrderByUid(){
        return String.format("http://localhost:%d/api/owners-tools/orders/get-order/orderUid",
                Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForGetOrderById(){
        return String.format("http://localhost:%d/api/owners-tools/orders/get-order/orderId",
                Integer.valueOf(serverPort));
    }


    protected  String getBaseUriForGetOrderByCustomer(){
        return String.format("http://localhost:%d/api/owners-tools/orders/get-order/customer",
                Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForOrderReady(){
        return String.format("http://localhost:%d/api/owners-tools/orders/order-ready", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForCloseOrder(){
        return String.format("http://localhost:%d/api/owners-tools/orders/close-order", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForDeleteOrder(){
        return String.format("http://localhost:%d/api/owners-tools/orders/delete-order", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForEditOrderItem(){
        return String.format("http://localhost:%d/api/owners-tools/orders/update-order-item",
                Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForAddOrderItem(){
        return String.format("http://localhost:%d/api/owners-tools/orders/add-to-order", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForSales(){
        return String.format("http://localhost:%d/api/owners-tools/orders/sales", Integer.valueOf(serverPort));
    }

    //============== owners-tools customer functions uris ====================//

    protected  String getBaseUriForGetAllCustomers(){
        return String.format("http://localhost:%d/api/owners-tools/customers/get-customers", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForGetCustomerByName(){
        return String.format("http://localhost:%d/api/owners-tools/customers/get-customer/name", Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForGetCustomerById(){
        return String.format("http://localhost:%d/api/owners-tools/customers/get-customer/customerId",
                Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForUpdateName(){
        return String.format("http://localhost:%d/api/owners-tools/customers/edit-customer-name",
                Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForUpdateEmail(){
        return String.format("http://localhost:%d/api/owners-tools/customers/edit-customer-email",
                Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForUpdatePhone(){
        return String.format("http://localhost:%d/api/owners-tools/customers/edit-customer-phone",
                Integer.valueOf(serverPort));
    }

    protected  String getBaseUriForDeleteCustomer(){
        return String.format("http://localhost:%d/api/owners-tools/customers/delete-customer",
                Integer.valueOf(serverPort));
    }
}