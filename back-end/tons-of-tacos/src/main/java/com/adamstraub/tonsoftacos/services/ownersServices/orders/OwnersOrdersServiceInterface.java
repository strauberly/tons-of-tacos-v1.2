package com.adamstraub.tonsoftacos.services.ownersServices.orders;
import com.adamstraub.tonsoftacos.dto.businessDto.DailySales;
import com.adamstraub.tonsoftacos.dto.businessDto.OrderReturnedToOwner;

import java.util.List;

public interface OwnersOrdersServiceInterface {

    List<OrderReturnedToOwner> getAllOrders();


    OrderReturnedToOwner getOrderByUid(String orderUid);


    List<OrderReturnedToOwner> getOpenOrderByCustomer(String customer);


//    OrderReturnedToOwner orderReady(Integer orderId);
OrderReturnedToOwner orderReady(String orderUid);


    OrderReturnedToOwner closeOrder(String orderUid);


//    String deleteOrder(Integer orderId);
String deleteOrder(String orderUid);



//    String addToOrder(Integer orderId, Integer menuItemId, Integer quantity);

    String addToOrder(String orderUid, Integer menuItemId, Integer quantity);


//    String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity);
String updateOrderItemQuantity(String orderUid, Integer orderItemId, Integer newQuantity);

    DailySales todaysSales();

}
