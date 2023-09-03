package com.adamstraub.tonsoftacos.services.ownersServices.orders;
import com.adamstraub.tonsoftacos.dto.businessDto.DailySales;
import com.adamstraub.tonsoftacos.dto.businessDto.OrderReturnedToOwner;

import java.util.List;

public interface OwnersOrdersServiceInterface {

    List<OrderReturnedToOwner> getAllOrders();


    OrderReturnedToOwner getOrderByUid(String orderUid);


    List<OrderReturnedToOwner> getOpenOrderByCustomer(String customer);


    OrderReturnedToOwner orderReady(Integer orderId);
//OrderReturnedToOwner orderReady(String orderId);


    OrderReturnedToOwner closeOrder(Integer orderId);


    String deleteOrder(Integer orderId);


    String addToOrder(Integer orderId, Integer menuItemId, Integer quantity);

    String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity);


    DailySales todaysSales();

}
