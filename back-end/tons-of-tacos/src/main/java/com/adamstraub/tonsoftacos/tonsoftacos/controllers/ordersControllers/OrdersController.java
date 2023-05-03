package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ordersControllers;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.NewOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.ReturnOrderToCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OrdersController implements OrdersControllerInterface {
    @Autowired
    private OrdersService ordersService;

    @Override
    public ReturnOrderToCustomerDto createOrder(@RequestBody NewOrderDto order) {
//        if (order.getCustomer().getName().matches("[a-z]|[A-Z]")) {
            System.out.println("controller");
            return ordersService.createOrder(order);
//        } else {
//            throw new IllegalArgumentException("fucker");
//        }
    }
}
