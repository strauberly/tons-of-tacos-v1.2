package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers.orders;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.DailySales;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.BusinessReturnedOrder;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.orders.OwnersOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class OwnersOrdersController implements OwnersOrdersControllerInterface {

    @Autowired
    private OwnersOrdersService ownersOrdersService;

    @Override
    public List<BusinessReturnedOrder> getAllOrders() {
        System.out.println("controller");
        return ownersOrdersService.getAllOrders();
    }

    @Override
    public BusinessReturnedOrder getOrderById(Integer orderId) {
        System.out.println("controller");
        return ownersOrdersService.getOrderById(orderId);
    }

    @Override
    public BusinessReturnedOrder getOrderByUid(@PathVariable String orderUid) {
        System.out.println("controller");
        return ownersOrdersService.getOrderByUid(orderUid);
    }

    @Override
    public List<BusinessReturnedOrder> getOpenOrderByCustomer(String customer) {
        System.out.println("controller");
        return ownersOrdersService.getOpenOrderByCustomer(customer);
    }

    @Override
    public BusinessReturnedOrder orderReady(Integer orderId) {
        System.out.println("controller");
        return ownersOrdersService.orderReady(orderId);
    }

    @Override
    public BusinessReturnedOrder closeOrder(Integer orderId) {
        System.out.println("controller");
//        System.out.println(orderId);
       return ownersOrdersService.closeOrder(orderId);
    }


    @Override
    public String deleteOrder(Integer orderId) {
        System.out.println("controller");
        return ownersOrdersService.deleteOrder(orderId);
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
