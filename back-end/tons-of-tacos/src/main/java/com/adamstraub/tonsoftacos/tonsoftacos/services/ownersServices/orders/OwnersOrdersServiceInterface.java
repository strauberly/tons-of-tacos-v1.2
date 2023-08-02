package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.orders;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.DailySales;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.BusinessReturnedOrder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OwnersOrdersServiceInterface {

    List<BusinessReturnedOrder> getAllOrders();


    BusinessReturnedOrder getOrderById(Integer orderId);


    BusinessReturnedOrder getOrderByUid(String orderUid);


    List<BusinessReturnedOrder> getOpenOrderByCustomer(String customer);


    BusinessReturnedOrder orderReady(Integer orderId);


    BusinessReturnedOrder closeOrder(Integer orderId);


    String deleteOrder(Integer orderId);


    String addToOrder(Integer orderId, Integer menuItemId, Integer quantity);

    String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity);


    DailySales todaysSales();

}
