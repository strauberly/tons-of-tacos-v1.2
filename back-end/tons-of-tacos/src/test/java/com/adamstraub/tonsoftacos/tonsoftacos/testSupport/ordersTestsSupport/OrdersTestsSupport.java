package com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ordersTestsSupport;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.TestUris;
import org.springframework.http.*;

public class OrdersTestsSupport extends TestUris {
    protected String validOrderBody() {
        return """
                {
                    "customer": {
                        "name": "billy billson",
                        "email": "billy@bolly.com",
                        "phoneNumber": "555.555.5959"
                    },
                    "order": {
                        "orderUid": "223-44-444",
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
    }

        protected String validCredentials(){
            return """
               {
               "username": "jcast22",
               "psswrd": "tacoocat"
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

