//package com.adamstraub.tonsoftacos.tonsoftacos.springTests.orderItemTests;
//
//import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
//import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.CreateOrderItemTestSupport;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.*;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.jdbc.SqlConfig;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//public class CreateOrderItemTest {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//    @Nested
//    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//    @Sql(scripts = {
//            "classpath:scripts/schema.sql",
//            "classpath:scripts/data.sql"},
//            config = @SqlConfig(encoding = "utf-8"))
//    class testThatDoesNotPolluteTheApplicationContext extends CreateOrderItemTestSupport {
//        @Test
//        void  createOrderItemWith201Response(){
//            String body = createOrderItemBody();
//            String uri = getBaseUriForCreateOrderItem();
//
//            System.out.println(uri);
//            System.out.println(createOrderItemBody());
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
//            ResponseEntity<OrderItem> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
//                    OrderItem.class);
//            System.out.println(response.toString());
//            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        }
//    }
//}
