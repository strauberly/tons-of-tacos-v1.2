package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;


public class BaseResponseTest {

    @Value("${local.server.port}")
    private String serverPort;


    @Autowired
    @Getter
    private TestRestTemplate restTemplate;

//============== menu item uris ====================//
        protected String getBaseUriForMenuItemByIdQuery(){
            return String.format("http://localhost:%d/api/menu/id", Integer.valueOf(serverPort));

        }
    protected String getBaseUriForMenuItemByCategoryQuery(){
        return String.format("http://localhost:%d/api/menu/category", Integer.valueOf(serverPort));

    }

//    ============== order item uris ====================//
    protected String getBaseUriForCreateOrderItem(){
        return String.format("http://localhost:%d/api/order/add-to-cart", Integer.valueOf(serverPort));

    }



}
