package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport;

import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.TestUris;

public class OwnersToolsTestsSupport extends TestUris {
//    holders for test bodies and auth tokens
    protected String validCredentials(){
        return """
               {
               "username": "jcast22",
               "psswrd": "tacoocat"
               }""";
    }
}
