package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ordersControllers;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class OrdersController implements OrdersControllerInterface {
    @Autowired
    private OrdersService ordersService;


    @Override
    public void createOrder(@RequestBody Orders order) {
        System.out.println("controller");
         ordersService.createOrder(order);
    }


    @Override
//    public List<Orders> getAllOrders() {
    public List<GetOrdersDto> getAllOrders() {
        System.out.println("controller");
        return ordersService.getAllOrders();
    }


    @Override
    public GetOrdersDto getOrderByUid(@PathVariable String orderUid) {
        System.out.println("controller");
        return ordersService.getOrderByUid(orderUid);

    }

//    @Override
//    public GetOrdersDto getOrderByCustomer(String customer) {
//        System.out.println("controller");
//        return ordersService.getOrderByCustomer(customer);
//
//    }

    @Override
    public String todaysSales() {
        System.out.println("controller");
        return ordersService.todaysSales();
    }

//    @Override
//    public void updateOrderByUid(String orderUid) {
//        System.out.println("controller");
//        ordersService.updateOrder(orderUid);
//    }


    @Override
    public void closeOrder(Integer orderId) {
        System.out.println("controller");
    ordersService.closeOrder(orderId);
    }

    @Override
    public void foodReady(Integer orderId) {
        System.out.println("controller");
        ordersService.foodReady(orderId);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        System.out.println("controller");
        ordersService.deleteOrder(orderId);
    }
}
