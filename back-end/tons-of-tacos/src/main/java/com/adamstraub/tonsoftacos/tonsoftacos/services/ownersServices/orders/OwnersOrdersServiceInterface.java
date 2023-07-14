package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.orders;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.DailySales;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.BusinessReturnedOrder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OwnersOrdersServiceInterface {

    @Transactional(readOnly = true)
    List<BusinessReturnedOrder> getAllOrders();

    @Transactional
    BusinessReturnedOrder getOrderById(Integer orderId);

    @Transactional(readOnly = true)
    BusinessReturnedOrder getOrderByUid(@PathVariable String orderUid);

    @Transactional
    List<BusinessReturnedOrder> getOpenOrderByCustomer(String customer);

    @Transactional
    BusinessReturnedOrder orderReady(Integer orderId);

    @Transactional
    BusinessReturnedOrder closeOrder(Integer orderId);

    @Transactional
    String deleteOrder(Integer orderId);

    @Transactional
    String addToOrder(Integer orderId, Integer menuItemId, Integer quantity);
//    @Transactional
    String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity);

    @Transactional
    DailySales todaysSales();

}
