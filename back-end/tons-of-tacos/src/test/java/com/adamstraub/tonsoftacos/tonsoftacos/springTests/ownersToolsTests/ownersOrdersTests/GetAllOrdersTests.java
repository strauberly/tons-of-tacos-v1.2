package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.ownersOrdersTests;

import com.adamstraub.tonsoftacos.tonsoftacos.controllers.ordersControllers.OrdersController;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
//import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.cartItemsTestsSupport.CartItemTestSupport;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;



class GetAllOrdersTests {
@Autowired
private JdbcTemplate jdbcTemplate;
@Nested
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test-application.properties")
@Sql(scripts = {
        "classpath:/test-schema.sql",
        "classpath:/test-data.sql",
},
        config = @SqlConfig(encoding = "utf-8"))
class testThatDoesNotPolluteTheApplicationContextUris extends OwnersToolsTestsSupport {
    @Test
            void allOrdersReturned200() {
//  Given: a successful connection and auth header
        //            get valid token
//        String token = validToken();
        String token = encryptedToken();
        Assertions.assertNotNull(token);

//           build auth header
        HttpHeaders authHeader = new HttpHeaders();
        authHeader.setContentType(MediaType.APPLICATION_JSON);
        authHeader.setBearerAuth(token);
        HttpEntity<String> headerEntity = new HttpEntity<>(authHeader);
//        When:  a successful connection made
        String uri =
                String.format("%s", getBaseUriForGetAllOrders());
        ResponseEntity<List<OwnersGetOrderDto>> response =
                getRestTemplate().exchange(uri, HttpMethod.GET, headerEntity, new ParameterizedTypeReference<>() {
                });
//  Then: a collection of the orders is returned with a response of 200
        System.out.println(response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(("Response code is " + response.getStatusCode() + "."));

    }




}
}