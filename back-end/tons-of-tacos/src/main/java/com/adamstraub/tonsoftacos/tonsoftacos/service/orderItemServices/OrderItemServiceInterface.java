package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface OrderItemServiceInterface {
    OrderItem addToCart(@RequestBody OrderItem orderItem);

    List<OrderItem>findByUuid(String orderUuid);

    OrderItem updateCart(@PathVariable Integer orderItemId, @RequestBody Integer newQuantity);

    @Transactional
    void removeCartItem(@PathVariable Integer orderItemId);
}
