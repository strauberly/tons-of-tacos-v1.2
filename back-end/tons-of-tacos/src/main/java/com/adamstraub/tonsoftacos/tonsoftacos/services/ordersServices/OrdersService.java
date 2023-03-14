package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.orderItemServices.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService implements OrdersServiceInterface {
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
    @Transactional
    public void  createOrder(@RequestBody Orders order){
        System.out.println("service");
        Double orderTotal = 0.00;
//        set totals for each order item
        List<OrderItem> newPriceForOrderItems = order.getOrderItems();
        for(OrderItem orderItem : newPriceForOrderItems){
            orderItem.setTotal(orderItem.getQuantity() * menuItemRepository
                    .getReferenceById(orderItem.getItemId()
                            .getId()).getUnitPrice());
//            calculate total for entire order
            orderTotal += orderItem.getTotal();
        }
        order.setOrderItems(newPriceForOrderItems);
        order.setOrderTotal(orderTotal);
        ordersRepository.save(order);
    }

    @Override
    @Transactional
//    public List<Orders> getAllOrders() {
    public List<GetOrdersDto> getAllOrders() {
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
//        return orders;
        return getOrderItemDtos;

    }

    @Override
    public GetOrdersDto getOrderByUid(String orderUid) {
        System.out.println("service");
        System.out.println(orderUid);
//            get order
        Orders order = ordersRepository.findByOrderUid(orderUid);
        GetOrdersDto orderDto = getOrderDtoConverter(order);
//            convert order to DTO
        System.out.println(order);
        System.out.println(orderDto);
//        return orderdto
        return getOrderDtoConverter(order);
    }

    @Override
    public GetOrdersDto getOrderByCustomer(String customer) {
        System.out.println("service");
        //        get by customer id where customer name = customer
//        System.out.println(customer);
        Customer customerObj = customerRepository.findByName(customer);
//        System.out.println(customerObj);
        Orders order = ordersRepository.findByCustomerId(customerObj.getCustomerId());
        GetOrdersDto orderDto = getOrderDtoConverter(order);
        System.out.println(order);
        System.out.println(orderDto);
        return  getOrderDtoConverter(order);
    }

    @Override
    public void foodReady(Integer orderId) {
        System.out.println("status");
        Orders order = ordersRepository.getReferenceById(orderId);
//        System.out.println(order);
        String timeReady = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        order.setReady(timeReady);
        System.out.println("Order up!");
    }

    @Override
    public void closeOrder(Integer orderId) {
        System.out.println("service");
        Orders order = ordersRepository.getReferenceById(orderId);
//        System.out.println(order);
        String statusUpdate = "closed";
        order.setStatus(statusUpdate);
        System.out.println("Order closed");
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
