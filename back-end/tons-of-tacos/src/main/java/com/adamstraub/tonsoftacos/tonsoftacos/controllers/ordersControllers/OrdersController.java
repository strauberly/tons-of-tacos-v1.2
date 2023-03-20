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
    public GetOrdersDto getOrderByUid(@PathVariable String orderUid) {
        System.out.println("controller");
        return ordersService.getOrderByUid(orderUid);

    }
}
