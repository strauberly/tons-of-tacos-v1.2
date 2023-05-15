package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport;

import com.adamstraub.tonsoftacos.tonsoftacos.services.security.JwtService;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.TestUris;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

public class OwnersToolsTestsSupport extends TestUris {
//    holders for test bodies and auth tokens

    private String secret;

    @Autowired
    JwtService jwtService = new JwtService();

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
               "psswrd":"?aNwlfCd7glf(E&r)lLr}W?fT#Ld?aFw_ic"
               }""";
    }

//    protected String badUsername(){
//        return """
//                {
//                "username": "tony",
//                "psswrd": "?aNwlfCd7glf(E&r)lLr}W?fT#Ld?aFw_ic"
//                }
//                """;
//    }

    protected String badUsername(){
        String badUsername = jwtService.encrypt("tony");
//        String badUsername = "tony";
        return
                "{ \"username\": " + '"' + badUsername + "\"," +  "\n" +
                "\"psswrd\" : \"?aNwlfCd7glf(E&r)lLr}W?fT\" " +
                "}";
    }

    protected String badPassword(){
        return """
                {
                "username": "m)Km7y{f0~nd$,hvNLOw0.F5FlP5u?5",
                "psswrd": "bigTony22"
                }
                """;
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

    protected String encryptedToken(){
        String body = encryptedCredentials();
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
