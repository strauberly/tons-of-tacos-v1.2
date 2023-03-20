package com.adamstraub.tonsoftacos.tonsoftacos.services.orderItemServices;
import org.springframework.transaction.annotation.Transactional;


public interface OrderItemServiceInterface {

    @Transactional
    void addToOrder(Integer orderId, Integer menuItemId, Integer quantity);
    @Transactional
    String updateOrderItem(Integer orderId, Integer orderItemId, Integer newQuantity);
}
