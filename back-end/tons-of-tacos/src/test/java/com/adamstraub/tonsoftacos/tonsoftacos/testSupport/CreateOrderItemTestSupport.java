package com.adamstraub.tonsoftacos.tonsoftacos.testSupport;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

public class CreateOrderItemTestSupport extends BaseResponseTest {
    protected String createOrderItemBody(){
        return "{\n"
                + " \"menu_item_id\" : \"1\",\n"
                + " \"menu_item_name\" : \"pound\",\n"
                + " \"order_uuid\" : \"45644-65465-46654\",\n"
                + " \"quantity\" : \"2\",\n"
                + " \"total\" : \"5.50\"\n"
                + "}";
    }


}
