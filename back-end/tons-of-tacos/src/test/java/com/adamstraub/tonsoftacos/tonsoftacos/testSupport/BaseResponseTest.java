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


        protected String getBaseUriForMenuItemQuery(){
            return String.format("http://localhost:%d/api/menu-item/search/", Integer.valueOf(serverPort));

        }

    protected String getBaseUriForMenuItemFastQuery(){
        return String.format("http://localhost:%d/api/menu-item/", Integer.valueOf(serverPort));

    }

    protected String getBaseUriForMenuItem(){
        return String.format("http://localhost:%d/api/menu-item/", Integer.valueOf(serverPort));

    }



}
