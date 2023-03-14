package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrdersServiceInterface {
    @Transactional
    void createOrder(@RequestBody Orders order);

//    @Transactional(readOnly = true)
//    List<Orders> getAllOrders();

    @Transactional(readOnly = true)
    List<GetOrdersDto> getAllOrders();

    @Transactional(readOnly = true)
    GetOrdersDto getOrderByUid(@PathVariable String orderUid);

    @Transactional(readOnly = true)
    GetOrdersDto getOrderByCustomer(@PathVariable String customer);

    @Transactional
    void foodReady(@PathVariable Integer orderId);
    @Transactional
    void closeOrder(@PathVariable Integer orderId);

    @Transactional
    String todaysSales();

    @Transactional
    void deleteOrder(Integer orderId);

//    @Transactional
//    void updateOrder(@PathVariable String orderUid);
}