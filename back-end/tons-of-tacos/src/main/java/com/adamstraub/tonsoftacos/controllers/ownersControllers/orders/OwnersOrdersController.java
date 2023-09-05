package com.adamstraub.tonsoftacos.controllers.ownersControllers.orders;

import com.adamstraub.tonsoftacos.dto.businessDto.DailySales;
import com.adamstraub.tonsoftacos.dto.businessDto.OrderReturnedToOwner;
import com.adamstraub.tonsoftacos.services.ownersServices.orders.OwnersOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class OwnersOrdersController implements OwnersOrdersControllerInterface {

    @Autowired
    private OwnersOrdersService ownersOrdersService;

    @Override
    public List<OrderReturnedToOwner> getAllOrders() {
        System.out.println("controller");
        return ownersOrdersService.getAllOrders();
    }


    @Override
    public OrderReturnedToOwner getOrderByUid(@PathVariable String orderUid) {
        System.out.println("controller");
        return ownersOrdersService.getOrderByUid(orderUid);
    }

    @Override
    public List<OrderReturnedToOwner> getOpenOrderByCustomer(String customer) {
        System.out.println("controller");
        return ownersOrdersService.getOpenOrderByCustomer(customer);
    }

    @Override
//    public OrderReturnedToOwner orderReady(Integer orderId) {
    public OrderReturnedToOwner orderReady(String orderUid) {
        System.out.println("controller");
        return ownersOrdersService.orderReady(orderUid);
//        return ownersOrdersService.orderReady(orderId);
    }

    @Override
//    public OrderReturnedToOwner closeOrder(Integer orderId) {
    public OrderReturnedToOwner closeOrder(String orderUid) {
            System.out.println("controller");
//        System.out.println(orderId);
       return ownersOrdersService.closeOrder(orderUid);
    }


    @Override
//    public String deleteOrder(Integer orderId) {
    public String deleteOrder(String orderUid) {
        System.out.println("controller");
        return ownersOrdersService.deleteOrder(orderUid);
    }

    @Override
    public String addToOrder(Integer orderId, Integer menuItemId, Integer quantity) {
        System.out.println("controller");
      return ownersOrdersService.addToOrder(orderId, menuItemId, quantity);
    }
//@Transactional
    @Override
    public String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity) {
        System.out.println("controller");
       return ownersOrdersService.updateOrderItemQuantity(orderId, orderItemId, newQuantity);
    }

    @Override
    public DailySales todaysSales() {
        System.out.println("controller");
        return ownersOrdersService.todaysSales();
    }
}