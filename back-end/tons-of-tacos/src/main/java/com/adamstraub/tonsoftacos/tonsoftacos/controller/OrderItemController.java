package com.adamstraub.tonsoftacos.tonsoftacos.controller;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
//import com.adamstraub.tonsoftacos.tonsoftacos.service.OrderItemService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderItemController {
//    @Autowired
//    private OrderItemService orderItemService;
    private OrderItemRepository orderItemRepository;

    @PostMapping("/add-to-cart")
    @ResponseStatus(code = HttpStatus.CREATED)
    public OrderItem addToCart(@RequestBody OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
    @GetMapping("/get-cart")
    @ResponseStatus(code = HttpStatus.OK)
    public List<OrderItem> getCart(@RequestBody OrderItem uuid){
        System.out.println(uuid);
        return orderItemRepository.orderUuid(String.valueOf(uuid));
    }

//    @Override
//    public List<OrderItem> getCart(String uuid) {
//        return orderItemRepository.findByOrder_UuidContaining(uuid);
//    }
}