package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface OrderItemServiceInterface {
    OrderItem addToCart(@RequestBody OrderItem orderItem);

    List<OrderItem>findByUuid(String orderUuid);

    OrderItem updateCartItem(@RequestBody OrderItem orderItem, Integer orderItemId);
}
