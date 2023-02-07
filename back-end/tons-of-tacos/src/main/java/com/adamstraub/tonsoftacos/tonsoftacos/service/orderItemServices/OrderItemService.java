package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
@Service
public class OrderItemService implements OrderItemServiceInterface {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public OrderItem addToCart(@RequestBody OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findByUuid(String orderUuid) {
        return orderItemRepository.findByOrderUuid(orderUuid);
    }

    @Override
    @Transactional
    public OrderItem updateCartItem(@RequestBody OrderItem orderItem, Integer orderItemId) {
//        quantity and total are updated on front end and then all backend will have to do is find the item and save
//        the changes.

        return null;
    }


}
