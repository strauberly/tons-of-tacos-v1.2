package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;


import java.util.InvalidPropertiesFormatException;
import java.util.List;

public interface OrderItemServiceInterface {
    @Transactional
    void addToCart(OrderItem orderItem) throws InvalidPropertiesFormatException;

    @Transactional(readOnly = true)
    List<GetOrderItemDto> findByCartUuid(String cartUuid);

    @Transactional
    OrderItemDto updateCart(@PathVariable Integer orderItemId, @RequestBody Integer newQuantity);

    @Transactional
    void removeCartItem(@PathVariable Integer orderItemId);
}
