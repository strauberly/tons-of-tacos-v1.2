package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers.orders;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersDailySalesDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.orders.OwnersOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class OwnersOrdersController implements OwnersOrdersControllerInterface {

    @Autowired
    private OwnersOrdersService ownersOrdersService;
//    List<ResponseEntity<OwnersGetOrderDto>>

    @Override
    @Transactional
    public List<OwnersGetOrderDto> getAllOrders() {
        System.out.println("controller");
        return ownersOrdersService.getAllOrders();
    }

//    @Override
//    @Transactional
//    public List<OwnersGetOrderDto> getAllOrders() {
//        System.out.println("controller");
//        return ownersOrdersService.getAllOrders();
//    }

    @Override
    public OwnersGetOrderDto getOrderById(Integer orderId) {
        System.out.println("controller");
        return ownersOrdersService.getOrderById(orderId);
    }

    @Override
    public OwnersGetOrderDto getOrderByUid(@PathVariable String orderUid) {
        System.out.println("controller");
        return ownersOrdersService.getOrderByUid(orderUid);
    }

    @Override
    public List<OwnersGetOrderDto> getOpenOrderByCustomer(String customer) {
        System.out.println("controller");
        return ownersOrdersService.getOpenOrderByCustomer(customer);
    }

    @Override
    public OwnersGetOrderDto orderReady(Integer orderId) {
        System.out.println("controller");
        return ownersOrdersService.orderReady(orderId);
    }

    @Override
    public OwnersGetOrderDto closeOrder(Integer orderId) {
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
    public OwnersDailySalesDto todaysSales() {
        System.out.println("controller");
        return ownersOrdersService.todaysSales();
    }
}
