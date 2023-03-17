package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.OwnersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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
    public OwnersGetOrderDto getOrderByUid(@PathVariable String orderUid) {
        System.out.println("controller");
        return ownersService.getOrderByUid(orderUid);
    }
//
    @Override
    public OwnersGetOrderDto getOrderByCustomer(String customer) {
        System.out.println("controller");
        return ownersService.getOpenOrderByCustomer(customer);
    }
//
    @Override
    public String todaysSales() {
        System.out.println("controller");
        return ownersService.todaysSales();
    }

    @Override
    public void foodReady(Integer orderId) {
        System.out.println("controller");
        ownersService.foodReady(orderId);
    }

    @Override
    public void closeOrder(Integer orderId) {
        System.out.println("controller");
        ownersService.closeOrder(orderId);
    }


    @Override
    public void deleteOrder(Integer orderId) {
        System.out.println("controller");
        ownersService.deleteOrder(orderId);
    }

    @Override
    public List<OwnersGetCustomerDto> getAllCustomers() {
        System.out.println("controller");
        return ownersService.getAllCustomers();
    }


}
