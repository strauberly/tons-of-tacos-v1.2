package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices.OrdersService;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.OwnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class OwnersController implements OwnersControllerInterface{

    @Autowired
    private OwnersService ownersService;

    @Override
    @Transactional
    public List<OwnersGetOrderDto> getAllOrders() {
        System.out.println("controller");
        return ownersService.getAllOrders();
    }

    @Override
    public Orders getOrderByUid(String orderUid) {
        return null;
    }

    @Override
    public Orders getOrderByCustomer(String customer) {
        return null;
    }

    @Override
    public String todaysSales() {
        return null;
    }

    @Override
    public void foodReady(Integer orderId) {

    }

    @Override
    public void closeOrder(Integer orderId) {

    }

    @Override
    public void deleteOrder(Integer orderId) {

    }
}
