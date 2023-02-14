//package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;//package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;
////
////import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
////import com.fasterxml.jackson.core.JsonProcessingException;
//import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.springframework.http.HttpStatus;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//
//
//import java.util.List;
//
//public interface OrderItemServiceInterface {
//    OrderItem addToCart(@RequestBody OrderItem orderItem) throws JsonProcessingException;
//
//    List<OrderItem>findByOrderUuid(String orderUuid);
//
//    OrderItem updateCart(@PathVariable Integer orderItemId, @RequestBody Integer newQuantity);
//
//    @Transactional
//    void removeCartItem(@PathVariable Integer orderItemId);
//}
