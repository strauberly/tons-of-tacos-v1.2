package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.orderItemServices.OrderItemService;
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
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    @Transactional
    public void  createOrder(@RequestBody Orders order){
        System.out.println("service");
        System.out.println(order);
        ordersRepository.save(order);
    }

    @Override
    @Transactional
    public List<Orders> getAllOrders() {
//    public List<GetOrdersDto> getAllOrders() {
//        get order and set orderitems
//        then get order items list and convert to dto list
//        convert order to order dto and set list of order item dto
        System.out.println("service");
//        Set<OrderItem> ordersItems = new HashSet<>();
        //        ordersItems.addAll(orderItemRepository.findAll());
        List<GetOrdersDto> getOrderItemDtos = new ArrayList<>();

        List<Orders> orders = ordersRepository.findAll();
        for (Orders order : orders) {
            order.setOrderItems(orderItemRepository.findAll());
            System.out.println(orders);
            getOrderItemDtos.add(getOrderDtoConverter(order));
            System.out.println("orders dto" + getOrderItemDtos);
        }
//        System.out.println(ordersItems);
//        System.out.println(orderItemRepository.findAll());
        System.out.println("orders" + orders);
        return orders;
//        return getOrderItemDtos;

    }
        private GetOrdersDto getOrderDtoConverter(Orders order) {
            GetOrdersDto getOrdersDto = new GetOrdersDto();

            getOrdersDto.setCustomerId(order.getCustomerId());
            getOrdersDto.setOrderTotal(order.getOrderTotal());
            getOrdersDto.setOrderUid(order.getOrderUid());
            getOrdersDto.setCreated(order.getCreated());
//            getOrdersDto.setOrderItems(order.getOrderItems());

            List<GetOrderItemDto> getOrderItemDtos = new ArrayList<>();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(orderItem -> getOrderItemDtos.add(orderItemService.getOrderItemDtoConversion(orderItem)));
            getOrdersDto.setOrderItems(getOrderItemDtos);
            return getOrdersDto;
        }



}
