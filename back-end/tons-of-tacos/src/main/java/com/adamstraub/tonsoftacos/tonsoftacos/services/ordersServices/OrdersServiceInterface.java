package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.NewOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrdersServiceInterface {
    @Transactional
    void createOrder(@RequestBody NewOrderDto order);


//    @Transactional
//    void createOrder(@RequestBody Orders order);

    @Transactional(readOnly = true)
    GetOrdersDto getOrderByUid(@PathVariable String orderUid);
}