package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

public interface OrdersServiceInterface {
    @Transactional
    void createOrder(@RequestBody Orders order);

    @Transactional(readOnly = true)
    List<Orders> getAllOrders();

//    @Transactional(readOnly = true)
//    List<GetOrdersDto> getAllOrders();
}
