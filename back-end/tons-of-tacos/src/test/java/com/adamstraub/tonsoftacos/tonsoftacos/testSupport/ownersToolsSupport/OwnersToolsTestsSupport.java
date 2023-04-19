package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport;

import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.TestUris;
import org.springframework.http.*;

public class OwnersToolsTestsSupport extends TestUris {
//    holders for test bodies and auth tokens



    protected String validCredentials(){

        return """
               {
               "username": "jcast22",
               "psswrd": "tacoocat"
               }""";
    }

    protected String encryptedCredentials(){
        return """
               {
               "username":"m)Km7y{f0~nd$,hvNLOw0.F5FlP5u?5",
               "psswrd":"tacoocat"
               }""";
    }
    protected String validToken(){
        String body = validCredentials();
        System.out.println(body);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> tokenEntity = new HttpEntity<>(body, headers);

        String uri = getBaseUriForOwnersLogin();
        ResponseEntity<String> response = getRestTemplate().exchange(uri, HttpMethod.POST, tokenEntity,
                String.class);
        return response.getBody();
    }
}
