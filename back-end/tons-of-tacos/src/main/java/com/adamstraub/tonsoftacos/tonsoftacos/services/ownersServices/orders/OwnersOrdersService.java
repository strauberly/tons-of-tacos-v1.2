package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.orders;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OwnersOrdersService implements OwnersOrdersServiceInterface {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OwnersGetOrderDto> getAllOrders() {
        System.out.println("service");
        List<OwnersGetOrderDto> orderItemDtos = new ArrayList<>();
        List<Orders> orders = ordersRepository.findAll();
                if (orders.size() == 0){
            throw new EntityNotFoundException("No orders found. Verify data integrity.");
        }else {

        for (Orders order : orders) {
//            System.out.println(orders);
            orderItemDtos.add(ownersGetOrderDtoConverter(order));
//            System.out.println("orders dto" + orderItemDtos);
        }
            return orderItemDtos;
        }
    }

    @Override
    public OwnersGetOrderDto getOrderById(Integer orderId) {
        System.out.println("service");
        try {
            return ownersGetOrderDtoConverter(ordersRepository.getReferenceById(orderId));
        }catch (Exception e){
            throw new EntityNotFoundException("Order with that id does not exist. Please verify id and try again.");
        }
        }

    @Override
    public OwnersGetOrderDto getOrderByUid(String orderUid) {
        System.out.println("service");
//        System.out.println(orderUid);
        Orders order = ordersRepository.findByOrderUid(orderUid);
        if (order == null){
            throw new EntityNotFoundException("No order found with that UID. Please verify and try again.");
        }else {
//        System.out.println(order);
            return ownersGetOrderDtoConverter(order);
        }
    }

    @Override
    public List<OwnersGetOrderDto> getOpenOrderByCustomer(String customer) {
        System.out.println("service");
        Customer customerObj;
        List<Orders> orders;
        try {
            customerObj = customerRepository.findByName(customer);
        }catch (Exception e){
            throw new EntityNotFoundException("Customer not found.");
        }

        try {
            orders = ordersRepository.findByCustomerId(customerObj.getCustomerId());
        } catch (Exception e){
            throw new EntityNotFoundException("No orders for customer found.");
        }

        List<OwnersGetOrderDto> openOrders = new ArrayList<>();
        for (Orders order: orders)
            if (order.getClosed().equals("open")) {
            openOrders.add(ownersGetOrderDtoConverter(order));
            }
        if (openOrders.size() > 0) {
            return openOrders;
        }else{
            throw new EntityNotFoundException("No open orders for customer found");
        }
    }

    @Override
    public String orderReady(Integer orderId) {
        System.out.println("service");
        Orders order;
        String response;
        try {
             order = ordersRepository.getReferenceById(orderId);
            System.out.println(order);
        }catch (Exception e){
            throw new EntityNotFoundException("Order does not exist. Please verify order id and try again.");
        }
        String timeReady = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        order.setReady(timeReady);
        System.out.println("Order up!");
        return response = "Order up!";
    }

    @Override
    public String closeOrder(Integer orderId) {
//        System.out.println(orderId);
        System.out.println("service");
        Orders order;
        String response;
        try {
            order = ordersRepository.getReferenceById(orderId);
            System.out.println(order);
        }catch (Exception e){
            throw new EntityNotFoundException("Order does not exist. Please verify order id and try again.");
        }

        if (order.getReady().equals("no")){
            throw new IllegalArgumentException("Order can not be closed if order is not ready.");
        }
//        Orders order = ordersRepository.getReferenceById(orderId);
        String timeClosed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        order.setClosed(timeClosed);
//        System.out.println("Order closed");
//        check against customer to see if there are other open orders and if not delete customer
        Customer customer = customerRepository.getReferenceById(order.getCustomerId());
        List<Orders> customerOrders = customer.getOrders();
//        System.out.println(customerOrders);
        List<Orders> openOrders = new ArrayList<>();
        for (Orders customerOrder : customerOrders) {
            if (customerOrder.getClosed().equals("open")){
                openOrders.add(customerOrder);
//                System.out.println(openOrders);
            }
        }
        if (openOrders.isEmpty()){
            customerRepository.deleteById(customer.getCustomerId());
//            System.out.println("All customer orders closed, customer information removed.");
        }
        return "All customer orders closed, customer information removed.";
    }

    @Override
    public void deleteOrder(Integer orderId) {
        System.out.println("service");
        try {
            Orders order = ordersRepository.getReferenceById(orderId);
            System.out.println(order);
        }catch (Exception e){
            throw new EntityNotFoundException("Can not delete order. Verify order id.");
        }
        ordersRepository.deleteById(orderId);
        System.out.println("Order deleted");
    }

    @Override
    public void addToOrder(Integer orderId, Integer menuItemId, Integer quantity) {
        System.out.println("service");
        Optional<MenuItem> menuItem;
        Optional<Orders> orderToUpdate;
        try{
            menuItem = Optional.of(menuItemRepository.getReferenceById(menuItemId));
            System.out.println(menuItem);
        }catch (Exception e){
            throw new EntityNotFoundException("Menu item can not be added to order. Verify menu item id.");
        }
        try{
            orderToUpdate = Optional.of(ordersRepository.getReferenceById(orderId));
            System.out.println(orderToUpdate);
        }catch (Exception e){
            throw new EntityNotFoundException("Menu item can not be added to order. Verify order id.");
        }


        OrderItem orderItem = OrderItem.builder()
                .itemId(menuItemRepository.getReferenceById(menuItemId))
                .quantity(quantity)
                .order(ordersRepository.getReferenceById(orderId)).build();
        orderItem.setTotal(menuItemRepository.getReferenceById(menuItemId).getUnitPrice() *
                orderItem.getQuantity());

//        System.out.println(orderItem);
        orderItemRepository.save(orderItem);
//
//        update order total
        Orders order  = ordersRepository.getReferenceById(orderId);
        order.setOrderTotal(order.getOrderTotal() + (menuItemRepository.getReferenceById(menuItemId).getUnitPrice() *
                quantity));
        ordersRepository.save(order);
//        System.out.println(order);
        System.out.println("Item added to order");
    }

    @Transactional
    @Override
    public String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity) {
        System.out.println("service");
//        try{
//            OrderItem orderItem = orderItemRepository.getByOrderItemId(orderItemId);
//            System.out.println(orderItem);
//        } catch (Exception e){
//            throw new EntityNotFoundException("umm");
//        }
        Orders order = ordersRepository.getById(orderId);
        if (order == null){
            throw new EntityNotFoundException("Order item not updated. Verify order exists.");
        }
        OrderItem orderItem = orderItemRepository.getByOrderItemId(orderItemId);
        if (orderItem == null){
            throw new EntityNotFoundException("Order item not updated. Verify order item is part of order.");
        }


//        Orders order;
        String response;
        System.out.println("new quantity: " + newQuantity);
//        try {
//            orderItem = orderItemRepository.getReferenceById(orderItemId);
//            System.out.println(orderItem);
//        }catch (Exception e) {
//            throw new EntityNotFoundException("Order item not updated. Verify order item id.");
//        }
//  checkout @transactional and clearing data, possibly chad
//        try {
////            order = ordersRepository.getById(orderId);
//            System.out.println(order);
//        }catch (Exception e) {
//            throw new EntityNotFoundException("Order item not updated. Verify order id.");
//        }

//        orderItem = orderItemRepository.getReferenceById(orderItemId);
//        order = ordersRepository.getReferenceById(orderId);
            if (newQuantity > 10) {
                System.out.println("quantity more than 10");
                throw new IllegalArgumentException("We were unable to process your request. " +
                        "Please contact us when trying to order more than 10 of any given item.");
            }


        if(newQuantity == 0){
//            try {
//            orderItem = orderItemRepository.getByOrderItemId(orderItemId);
//            try {
//                orderItem = orderItemRepository.getByOrderItemId(orderItemId);
//            } catch (Exception e){
//                throw new EntityNotFoundException("umm");
//            }
                System.out.println("order item: " + orderItem);
                System.out.println("old order: " + order.getOrderItems());
//                order.setOrderTotal(order.getOrderTotal() - orderItem.getTotal());
                orderItemRepository.delete(orderItem);
            order.setOrderTotal(order.getOrderTotal() - orderItem.getTotal());

//            orderItem = orderItemRepository.getByOrderItemId(orderItem.getOrderItemId());
//            ordersRepository.save(order);
//            System.out.println("order item" + orderItemRepository.getByOrderItemId(orderItem.getOrderItemId()));
//            List<OrderItem> updatedOrder = order.getOrderItems();
//            order.setOrderItems(updatedOrder);
                System.out.println("new order: " + order.getOrderItems());
                response = "Item quantity updated, item removed, cart updated.";
//            } catch (Exception e) {
//                throw new EntityNotFoundException("ummm");
//            }
            }else{
            orderItem.setQuantity(newQuantity);
            orderItem.setTotal(menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice() *
                    orderItem.getQuantity());
            System.out.println("old total: " + ordersRepository.getReferenceById(orderId).getOrderTotal());
            order.setOrderTotal(order.getOrderTotal() + orderItem.getTotal());
            orderItemRepository.save(orderItem);
            ordersRepository.save(order);
            System.out.println("new total: " + ordersRepository.getReferenceById(orderId).getOrderTotal());
            response = "Item quantity updated, cart updated.";
        }
//        System.out.println(order);
//        ordersRepository.save(order);
        System.out.println(response);
//        order = null;
        return response;
    }


    @Override
    public String todaysSales() {
        System.out.println("service");

        String formattedSales;
        LocalDate todaysDate = LocalDate.now();
        LocalDate dbDate;
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("E dd MMM yyyy");
        Double salesTotal = 0.00;
        List<Orders> todaysOrders = new ArrayList<>();

        List<Orders> completedOrders = ordersRepository.findByClosed();
        for (Orders completedOrder: completedOrders){
            dbDate = completedOrder.getCreated().toLocalDateTime().toLocalDate();
            if(todaysDate.format(formattedDate).equals(dbDate.format(formattedDate))){
                todaysOrders.add(completedOrder);
            }
        }
//                create jpa query that takes today's date and closed closed
        for (Orders order:todaysOrders){
            salesTotal += order.getOrderTotal();
        }

        String numberOfOrders = String.valueOf(todaysOrders.size());
        formattedSales = "For: " + todaysDate.format(formattedDate) + ", Number of sales: " + numberOfOrders + ", " +
                "Totaling: $" + salesTotal;
        return formattedSales;
    }

    private OwnersGetOrderDto ownersGetOrderDtoConverter(Orders order) {
        OwnersGetOrderDto ownersGetOrderDto = new OwnersGetOrderDto();
//        set the dto
        ownersGetOrderDto.setOrderId(order.getOrderId());
        if (order.getCustomerId() != null) {
            ownersGetOrderDto.setName(customerRepository.getReferenceById(order.getCustomerId()).getName());
            ownersGetOrderDto.setEmail(customerRepository.getReferenceById(order.getCustomerId()).getEmail());
            ownersGetOrderDto.setPhone(customerRepository.getReferenceById(order.getCustomerId()).getPhoneNumber());
        }
        ownersGetOrderDto.setOrderUid(order.getOrderUid());

//        set the get order items dto
        List<OwnersOrderItemDto> ownersOrderItemDtos = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItems();

        orderItems.forEach(orderItem -> ownersOrderItemDtos.add(ownersOrderItemDtoConvertor(orderItem)));

        ownersGetOrderDto.setOrderItems(ownersOrderItemDtos);
        ownersGetOrderDto.setOrderTotal(order.getOrderTotal());
        ownersGetOrderDto.setCreated(order.getCreated());
        ownersGetOrderDto.setReady(order.getReady());
        ownersGetOrderDto.setClosed(order.getClosed());
//        System.out.println(ownersGetOrderDto);
        return ownersGetOrderDto;
    }

    private OwnersOrderItemDto ownersOrderItemDtoConvertor(OrderItem orderItem){
        OwnersOrderItemDto ownersOrderItemDto = new OwnersOrderItemDto();

        ownersOrderItemDto.setOrderItemId(orderItem.getOrderItemId());
        ownersOrderItemDto.setItemName(orderItem.getItemId().getItemName());
        ownersOrderItemDto.setQuantity(orderItem.getQuantity());
        ownersOrderItemDto.setTotal(orderItem.getTotal());
//        System.out.println(ownersOrderItemDto);
        return ownersOrderItemDto;
    }
}
