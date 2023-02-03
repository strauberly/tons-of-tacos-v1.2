package com.adamstraub.tonsoftacos.tonsoftacos.service;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItem;

public interface OrderService {
    OrderItem addToCart(com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem orderItem);
}
