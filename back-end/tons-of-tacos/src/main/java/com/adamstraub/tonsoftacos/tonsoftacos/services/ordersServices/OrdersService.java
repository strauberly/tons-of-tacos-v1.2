package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrdersService implements OrdersServiceInterface {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    @Transactional
    public void  createOrder(@RequestBody Orders order){
        ordersRepository.save(order);
    }

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

}
