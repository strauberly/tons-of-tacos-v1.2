package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.orders;
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
    String orderReady(Integer orderId);

    @Transactional
    void closeOrder(Integer orderId);

    @Transactional
    void deleteOrder(Integer orderId);

    @Transactional
    void addToOrder(Integer orderId, Integer menuItemId, Integer quantity);
//    @Transactional
    String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity);

    @Transactional
    String todaysSales();

}
