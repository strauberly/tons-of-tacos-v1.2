//package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;//package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;
////import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
////import com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices.OrderItemService;
//import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
//import com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices.OrderItemService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//public class OrderItemController implements OrderItemControllerInterface {
//@Autowired
//    private OrderItemService orderItemService;
//    @Override
//    public OrderItem addToCart(OrderItem orderItem) throws JsonProcessingException {
//        return orderItemService.addToCart(orderItem);
//    }
//    @Override
//    public List<OrderItem> findByOrderUuid(String  orderUuid) {
//        return orderItemService.findByOrderUuid(orderUuid);
//    }
//
//    @Override
//    public OrderItem updateCart(Integer orderItemId, Integer newQuantity) {
//        return orderItemService.updateCart(orderItemId, newQuantity);
//    }
//
//    @Override
//    public void removeCartItem(Integer orderItemId) {
//    orderItemService.removeCartItem(orderItemId);
//    }
//}