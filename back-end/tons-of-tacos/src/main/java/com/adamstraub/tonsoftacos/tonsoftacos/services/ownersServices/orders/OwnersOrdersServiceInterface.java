package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.orders;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersDailySalesDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OwnersOrdersServiceInterface {

    @Transactional(readOnly = true)
    List<OwnersGetOrderDto> getAllOrders();

    @Transactional
    OwnersGetOrderDto getOrderById(Integer orderId);

    @Transactional(readOnly = true)
    OwnersGetOrderDto getOrderByUid(@PathVariable String orderUid);

    @Transactional
    List<OwnersGetOrderDto> getOpenOrderByCustomer(String customer);

    @Transactional
    OwnersGetOrderDto orderReady(Integer orderId);

    @Transactional
    OwnersGetOrderDto closeOrder(Integer orderId);

    @Transactional
    void deleteOrder(Integer orderId);

    @Transactional
    void addToOrder(Integer orderId, Integer menuItemId, Integer quantity);
//    @Transactional
    String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity);

    @Transactional
    OwnersDailySalesDto todaysSales();

}
