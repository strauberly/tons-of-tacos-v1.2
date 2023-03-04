package com.adamstraub.tonsoftacos.tonsoftacos.springTests.orderItemTests;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.OrderItemTestSupport;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GetOrderItemsTests{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @TestPropertySource("classpath:application.properties")
    @Sql(scripts = {
            "classpath:/test-schema.sql",
            "classpath:/test-data.sql",
    },
            config = @SqlConfig(encoding = "utf-8"))

    class doesNotPolluteTheApplicationContextIT extends OrderItemTestSupport {

        @Test
        void orderItemsReturnedWithValidUuidDto200() {
//      Given: a valid uuid
//            rewrite url for query -> match postman
            String validUuid = "654654-4655-555";
            String parameter = "cartUuid";

//      When: a successful connection is made

            String uri =
                    String.format("%s?%s=%s", getBaseUriForGetCartItemsByUuid(), parameter, validUuid);
            System.out.println(uri);

            ResponseEntity<List<GetOrderItemDto>> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                    });

//      Then: a collection of order items is returned with a matching uuid and status of 200
            System.out.println(("Response code is " + response.getStatusCode() + "."));
            System.out.println(response.getBody());
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        }


        @Test
        void orderItemsNotReturnedWithInvalidUuid4XX() {
//      Given: a valid uuid
//            rewrite url for query -> match postman
            String invalidUuid = "6555ffd$-45-555";
            String parameter = "cartUuid";

//      When: a successful connection is made

            String uri =
                    String.format("%s?%s=%s", getBaseUriForGetCartItemsByUuid(), parameter, invalidUuid);
            System.out.println(uri);

            ResponseEntity<Map<String, Object>> response =
                    getRestTemplate().exchange(uri, HttpMethod.GET, null,
                            new ParameterizedTypeReference<>() {
                            });

//      Then: nothing is returned and status of 4xx
            System.out.println("Response code is " + response.getStatusCode() + ".");
            assertThat(response.getStatusCode().is4xxClientError());
        }

}
}
