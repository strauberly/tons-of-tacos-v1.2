package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.orders;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.DailySales;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.OrderReturnedToOwner;

import java.util.List;

public interface OwnersOrdersServiceInterface {

    List<OrderReturnedToOwner> getAllOrders();


    OrderReturnedToOwner getOrderById(Integer orderId);


    OrderReturnedToOwner getOrderByUid(String orderUid);


    List<OrderReturnedToOwner> getOpenOrderByCustomer(String customer);


    OrderReturnedToOwner orderReady(Integer orderId);


    OrderReturnedToOwner closeOrder(Integer orderId);


    String deleteOrder(Integer orderId);


    String addToOrder(Integer orderId, Integer menuItemId, Integer quantity);

    String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity);


    DailySales todaysSales();

}
