package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ordersControllers;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class OrdersController implements OrdersControllerInterface {
    @Autowired
    private OrdersService ordersService;

    @Transactional
    @Override
    public void createOrder() {
    }

    @Override
    public List<Orders> getAllOrders() {
        System.out.println("controller");
        return ordersService.getAllOrders();
    }

    @Override
    public void getOrderById(Orders order) {

    }

    @Override
    public void getOrderByCustomer(Orders order) {

    }

    @Override
    public void updateOrderByUid(Orders order) {

    }

    @Override
    public void deleteOrder(Orders order) {

    }

}
