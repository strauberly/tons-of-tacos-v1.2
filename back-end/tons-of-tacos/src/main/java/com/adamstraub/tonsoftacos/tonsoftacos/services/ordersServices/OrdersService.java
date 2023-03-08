package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.cartItemServices.OrderItemService;
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
    public List<GetOrdersDto> getAllOrders() {
        System.out.println("service");
// get orders and convert to dto
        List<GetOrdersDto> getOrderItemDtos = new ArrayList<>();
        List<Orders> orders = ordersRepository.findAll();
        for (Orders order : orders) {
            order.setOrderItems(orderItemRepository.findAll());
            System.out.println(orders);
            getOrderItemDtos.add(getOrderDtoConverter(order));
            System.out.println("orders dto" + getOrderItemDtos);
        }
        return getOrderItemDtos;

    }
        private GetOrdersDto getOrderDtoConverter(Orders order) {
            GetOrdersDto getOrdersDto = new GetOrdersDto();
            getOrdersDto.setCustomerId(order.getCustomerId());
            getOrdersDto.setOrderTotal(order.getOrderTotal());
            getOrdersDto.setOrderUid(order.getOrderUid());
            getOrdersDto.setCreated(order.getCreated());
//            convert order items to their dto form and then set dto field
            List<GetOrderItemDto> getOrderItemDtos = new ArrayList<>();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.forEach(orderItem -> getOrderItemDtos.add(orderItemService.getOrderItemDtoConversion(orderItem)));
            getOrdersDto.setOrderItems(getOrderItemDtos);
            return getOrdersDto;
        }
}
