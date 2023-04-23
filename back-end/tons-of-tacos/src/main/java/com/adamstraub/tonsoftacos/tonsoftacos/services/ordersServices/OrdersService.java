package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.NewOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.ReturnOrderToCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.apache.commons.lang3.ArrayUtils;
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
    private MenuItemRepository menuItemRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public ReturnOrderToCustomerDto createOrder(@RequestBody NewOrderDto order){
        System.out.println("service");
        System.out.println("new order: " + order);
        System.out.println("order items: " + order.getOrder().getOrderItems());
//        System.out.println(genOrderUid());
        Double orderTotal = 0.00;

        ReturnOrderToCustomerDto customerDto = new ReturnOrderToCustomerDto();
        GetOrderItemDto orderitem = new GetOrderItemDto();
        Orders newOrder = order.getOrder();
        Orders orderConfirmation;
//        System.out.println(newOrder);
        List<OrderItem> orderItems = newOrder.getOrderItems();
        List<GetOrderItemDto> orderItemDtos = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            orderItem.setTotal(orderItem.getQuantity() *
                    menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice());
            System.out.println(orderItem.getTotal());
            orderTotal += orderItem.getTotal();
        }

        Customer newCustomer = order.getCustomer();
//        System.out.println(newCustomer);
        if (customerRepository.findByName(newCustomer.getName()) != null) {
            newOrder.setCustomerId(customerRepository.findByName(newCustomer.getName()).getCustomerId());
        }else{
            customerRepository.save(newCustomer);
            newCustomer = customerRepository.findByName(newCustomer.getName());
//            System.out.println(newCustomer);
            newOrder.setCustomerId(newCustomer.getCustomerId());
        }
        newOrder.setOrderItems(orderItems);
        newOrder.setOrderTotal(orderTotal);
//        set order uid
        newOrder.setOrderUid(genOrderUid());
        System.out.println("Order created.");
        System.out.println(newOrder.getOrderItems());
        ordersRepository.save(newOrder);

        orderConfirmation = ordersRepository.findByOrderUid(newOrder.getOrderUid());
        System.out.println("order confirmation: " + orderConfirmation.getOrderItems());
        customerDto.setCustomerName(newCustomer.getName());
        customerDto.setOrderUuid(newOrder.getOrderUid());
        customerDto.setOrderTotal(newOrder.getOrderTotal());

        orderItems = orderConfirmation.getOrderItems();
        List<GetOrderItemDto> itemConfirmationDtos = new ArrayList<>();
        for (OrderItem orderItem : orderItems){
//            orderItemDtos.add(orderItemDtoConvertor(orderItem));
            itemConfirmationDtos.add(orderItemDtoConvertor(orderItem));
        }
        System.out.println("item dto confirmation: " + itemConfirmationDtos);

//        customerDto.setOrderItems(orderItemDtos);
//        System.out.println(customerDto);
        return customerDto;
    }
    private GetOrderItemDto orderItemDtoConvertor(OrderItem orderItem){
        GetOrderItemDto orderItemDto = new GetOrderItemDto();
//        System.out.println(orderItem);
//        System.out.println(orderItem.getTotal());
        orderItemDto.setItemName(menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getItemName());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setTotal(orderItem.getTotal());
        orderItemDto.setUnitPrice(menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice());
//        System.out.println(orderItemDto);
//        System.out.println("orderitemdto: " + orderItemDto);
        return orderItemDto;
    }

//    @Override
//    @Transactional
//    public void  createOrder(@RequestBody NewOrderDto order){
//        System.out.println("service");
////        System.out.println(order);
////        System.out.println(genOrderUid());
//        Double orderTotal = 0.00;
//
//        Orders newOrder = order.getOrder();
////        System.out.println(newOrder);
//        List<OrderItem> orderItems = newOrder.getOrderItems();
//        for (OrderItem orderItem : orderItems) {
//            orderItem.setTotal(orderItem.getQuantity() *
//                    menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice());
//            orderTotal += orderItem.getTotal();
//        }
//
//        Customer newCustomer = order.getCustomer();
////        System.out.println(newCustomer);
//        if (customerRepository.findByName(newCustomer.getName()) != null) {
//            newOrder.setCustomerId(customerRepository.findByName(newCustomer.getName()).getCustomerId());
//        }else{
//            customerRepository.save(newCustomer);
//            newCustomer = customerRepository.findByName(newCustomer.getName());
////            System.out.println(newCustomer);
//            newOrder.setCustomerId(newCustomer.getCustomerId());
//        }
//        newOrder.setOrderItems(orderItems);
//        newOrder.setOrderTotal(orderTotal);
////        set order uid
//        newOrder.setOrderUid(genOrderUid());
//        System.out.println("Order created.");
//        System.out.println(newOrder);
//        ordersRepository.save(newOrder);
//    }

//    check randomness in

    public String genOrderUid() {
        String orderUid = null;
//        example: 11A32
//        gen a random and add to byte array
//        convert byte array to a char array
//        convert char array to string
//        search for orders by uid using the generated uid
//        if the return is null then return the new uid
//        if is null == false genorderUid
        StringBuilder orderUidBuilder = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            orderUid = String.valueOf(orderUidBuilder.append(randomUidChar()));
        }

//        System.out.println("orderUid: " + orderUid);
//        compare uid against others by doing a find by uid and if null then return if not re-run
        if (ordersRepository.findByOrderUid(orderUid) != null) {
            genOrderUid();
        }
        return orderUid;
    }

//    /rewrite and try
    private char randomUidChar() {
        int min = 48, max = 90;
        int random = (int) (Math.random() * ((max - min)) + min);
        char randomChar;
        int[] excluded = {58, 59, 60, 61, 62, 63, 64};
        if (ArrayUtils.contains(excluded, random)) {
            randomChar = randomUidChar();
            return randomChar;
        }
        return (char) random;
    }
////            System.out.println("gened uid: " + randomChars);
//        System.out.println("random char: " + (char)random);
//        return (char)random;
//    }
////rewrite and try
//    private char randomUidChar() {
//        int min = 48, max = 90;
////        int random = min + (int) (Math.random() * ((max - min)) + 1);
//        int random = (int) (Math.random() * ((max - min)) + min);
//        int[] excluded = {58, 59, 60, 61, 62, 63, 64};
//        List<Character> randomChars = new ArrayList<>();
//        boolean excludedInt = false;
//        String randomUid;
//        char choice = 0;
////        for (int i = 0; i < 5; i++) {
//            for (int ex : excluded) {
//                choice = (random == ex ? randomUidChar() : (char) random);
//                excluded.
//////                randomChars.add(choice);
//                if (ArrayUtils.contains(excluded, random)) {
//                    randomUidChar();
//                }
//            }
////            System.out.println("gened uid: " + randomChars);
//        System.out.println((char)random);
//            return (char)random;
//    }

}
