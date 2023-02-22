package com.adamstraub.tonsoftacos.tonsoftacos.springTests.orderItemTests;

import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.OrderItemTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

class GetOrderItemsTests{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @TestPropertySource("classpath:application-test.properties")
    @Sql(scripts = {
            "classpath:/test-schema.sql",
            "classpath:/test-data.sql",
    },
            config = @SqlConfig(encoding = "utf-8"))
    static
    class doesNotPolluteTheApplicationContextIT extends OrderItemTestSupport {
        @Test
        void orderItemsReturnedWithValidUuid200() {
//      Given: a valid uuid
//            rewrite url for query -> match postman
            String uuid = "654654-4655-555";
            String parameter = "uuid";

//      When: a successful connection is made

            String uri =
                    String.format("%s%s?%s=%s", getBaseUriForGetOrderItemsByUuid(), parameter, parameter, uuid);
            System.out.println(uri);


//      Then: a collection of order items is returned with a matching uuid and status of 200


        }
    }
}
