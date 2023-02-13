package com.adamstraub.tonsoftacos.tonsoftacos.springTests.menuItemTests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.Exchanger;

public class GetMenuItemsByCategoryTests {


    @Test
    void testThatMenuItemsAreReturnedByCategoryWith200() {
        System.out.println(getBaseUriForMenuItemQuery());
//        Given: a valid menu item category
        String category = "taco";
        String parameter = "category";
        String uri =
                String.format("%s%s?%s=%s", getBaseUriForMenuItemQuery(), parameter, parameter, category);
        System.out.println(uri);
//         When: when connection is made to uri
        ResponseEntity<Object> response =
                getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
//            Then: a 200 status code is returned
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private boolean getBaseUriForMenuItemQuery() {
        return false;
    }

    private TestRestTemplate getRestTemplate() {
        return null;
    }
}
