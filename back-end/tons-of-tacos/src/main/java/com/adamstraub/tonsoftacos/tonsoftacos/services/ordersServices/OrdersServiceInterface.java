package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrdersServiceInterface {
    @Transactional
    void createOrder(Orders order);

    @Transactional(readOnly = true)
    List<Orders> getAllOrders();
}
