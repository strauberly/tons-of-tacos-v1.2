package com.adamstraub.tonsoftacos.tonsoftacos.service;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//@Service
//public class OrderItemService implements OrderItemServiceInterface {
//    @Autowired
////    private OrderItemRepository orderItemRepository;
////    @Transactional(readOnly = true)
////    @Override
////    public List<OrderItem> orderItems(String uuid) {
////        return orderItemRepository.findByOrder_UuidContaining(uuid);
////    }
//}
