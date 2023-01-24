package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

public class BaseTest {

@LocalServerPort

    private int serverPort;

@Autowired
@Getter
private TestRestTemplate restTemplate;

protected String getBaseUriForMenuItem(){
    return String.format("http://localhost:%d/api/menu-item", serverPort);
}

}
