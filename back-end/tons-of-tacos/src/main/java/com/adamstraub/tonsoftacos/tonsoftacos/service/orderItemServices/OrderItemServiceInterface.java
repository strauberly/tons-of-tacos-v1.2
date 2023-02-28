package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;//package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;
//
//import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
//import com.fasterxml.jackson.core.JsonProcessingException;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.http.HttpEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.InvalidPropertiesFormatException;
import java.util.List;

public interface OrderItemServiceInterface {
    @Transactional
    OrderItem addToCart(OrderItem orderItem) throws InvalidPropertiesFormatException;

    @Transactional(readOnly = true)
    List<OrderItem>findByOrderUuid(String orderUuid);

    @Transactional
    OrderItem updateCart(@PathVariable Integer orderItemId, @RequestBody Integer newQuantity);
//
    @Transactional
    OrderItem removeCartItem(@PathVariable Integer orderItemId);
}
