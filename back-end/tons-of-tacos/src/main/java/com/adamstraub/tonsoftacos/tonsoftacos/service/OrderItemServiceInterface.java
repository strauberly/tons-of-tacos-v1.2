package com.adamstraub.tonsoftacos.tonsoftacos.service;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;

import java.util.List;

public interface OrderItemServiceInterface {
    List<OrderItem> orderItems(String uuid);
}
