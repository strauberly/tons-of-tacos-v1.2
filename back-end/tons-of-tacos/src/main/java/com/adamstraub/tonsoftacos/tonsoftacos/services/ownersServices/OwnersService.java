package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.orderItemServices.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class OwnersService implements OwnersServiceInterface {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private OrderItemService orderItemService;
    @Override
    @Transactional(readOnly = true)
    public List<OwnersGetOrderDto> getAllOrders() {
//        get order and set orderitems
//        then get order items list and convert to dto list
//        convert order to order dto and set list of order item dto
        System.out.println("service");
//        Set<OrderItem> ordersItems = new HashSet<>();
        //        ordersItems.addAll(orderItemRepository.findAll());
        List<OwnersGetOrderDto> getOrderItemDtos = new ArrayList<>();
        List<Orders> orders = ordersRepository.findAll();
//        List<OrderItem> orderItems = new ArrayList<>();
        for (Orders order : orders) {
//            List<OrderItem> orderItems = new ArrayList<>();
//            orderItems = (orderItemRepository.findByOrder(order.getOrderId()));
//            order.setOrderItems();
            System.out.println(orders);
            getOrderItemDtos.add(ownersGetOrderDtoConverter(order));
            System.out.println("orders dto" + getOrderItemDtos);
        }
//        System.out.println(ordersItems);
//        System.out.println(orderItemRepository.findAll());
        System.out.println("orders" + orders);
//        return orders;
        return getOrderItemDtos;

//    public List<Orders> getAllOrders() {
////        get order and set orderitems
////        then get order items list and convert to dto list
////        convert order to order dto and set list of order item dto
//            System.out.println("service");
////        list<OrderItem> ordersItems = new HashSet<>();
////                    ordersItems.addAll(orderItemRepository.findAll());
//////            List<GetOrdersDto> getOrderItemDtos = new ArrayList<>();
//        List<Orders> orders = new ArrayList<>(ordersRepository.findAll());
////        orders = ordersRepository.findAll();
////            List<Orders> orders = ordersRepository.findAll();
////            for (Orders order : orders) {
////                order.setOrderItems(orderItemRepository.findAll());
////                System.out.println(orders);
////                getOrderItemDtos.add(getOrderDtoConverter(order));
////                System.out.println("orders dto" + getOrderItemDtos);
////            }
////        System.out.println(ordersItems);
////        System.out.println(orderItemRepository.findAll());
//            System.out.println("Orders: " + orders);
//        return orders;
////            return getOrderItemDtos;
    }

    private OwnersGetOrderDto ownersGetOrderDtoConverter(Orders order) {
        OwnersGetOrderDto ownersGetOrderDto = new OwnersGetOrderDto();
//        set the dto
        ownersGetOrderDto.setOrderId(order.getOrderId());
        ownersGetOrderDto.setName(customerRepository.getReferenceById(order.getCustomerId()).getName());
        ownersGetOrderDto.setEmail(customerRepository.getReferenceById(order.getCustomerId()).getEmail());
        ownersGetOrderDto.setPhone(customerRepository.getReferenceById(order.getCustomerId()).getPhoneNumber());
        ownersGetOrderDto.setOrderUid(order.getOrderUid());
//        ownersGetOrderDto.setCustomerId(order.getCustomerId());
//        ownersGetOrderDto.setOrderTotal(order.getOrderTotal());
//        ownersGetOrderDto.setOrderUid(order.getOrderUid());
//        ownersGetOrderDto.setCreated(order.getCreated());
////            ownersGetOrderDto.setOrderItems(order.getOrderItems());

//        set the get order items dto
        List<OwnersOrderItemDto> ownersOrderItemDtos = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.forEach(orderItem -> ownersOrderItemDtos.add(ownersOrderItemDtoConvertor(orderItem)));
        ownersGetOrderDto.setOrderItems(ownersOrderItemDtos);
//
        ownersGetOrderDto.setOrderTotal(order.getOrderTotal());
        ownersGetOrderDto.setCreated(order.getCreated());
        ownersGetOrderDto.setReady(order.getReady());
        ownersGetOrderDto.setStatus(order.getStatus());
        System.out.println(ownersGetOrderDto);
        return ownersGetOrderDto;
    }

    private OwnersOrderItemDto ownersOrderItemDtoConvertor(OrderItem orderItem){
        OwnersOrderItemDto ownersOrderItemDto = new OwnersOrderItemDto();
        ownersOrderItemDto.setOrderItemId(orderItem.getOrderItemId());
        ownersOrderItemDto.setItemName(orderItem.getItemId().getItemName());
        ownersOrderItemDto.setQuantity(orderItem.getQuantity());
        ownersOrderItemDto.setTotal(orderItem.getTotal());


//        ownersOrderItemDto.setUnitPrice(orderItem.getItemId().getUnitPrice());
//        ownersOrderItemDto.setItemName(orderItem.getItemId().getItemName());
//        ownersOrderItemDto.setTotal(orderItem.getTotal());
//        ownersOrderItemDto.setQuantity(orderItem.getQuantity());
        System.out.println(ownersOrderItemDto);
        return ownersOrderItemDto;
    }
}
