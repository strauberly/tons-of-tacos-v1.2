package com.adamstraub.tonsoftacos.services.ownersServices.orders;
import com.adamstraub.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.dto.businessDto.DailySales;
import com.adamstraub.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.dto.businessDto.OrderReturnedToOwner;
import com.adamstraub.tonsoftacos.dto.businessDto.OrderItemReturnedToOwner;
import com.adamstraub.tonsoftacos.entities.Customer;
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
    public List<OrderReturnedToOwner> getAllOrders() {
        System.out.println("service");
        List<OrderReturnedToOwner> orderItemDtos = new ArrayList<>();
        List<Orders> orders = ordersRepository.findAll();
        try{
            for (Orders order : orders) {
            orderItemDtos.add(ownersGetOrderDtoConverter(order));
        }
            return orderItemDtos;
        } catch (Exception e){
            throw new EntityNotFoundException("No orders found. Verify data integrity.");
        }
    }

    @Override
    public OrderReturnedToOwner getOrderById(Integer orderId) {
        System.out.println("service");
        try {
            return ownersGetOrderDtoConverter(ordersRepository.getReferenceById(orderId));
        }catch (Exception e){
            throw new EntityNotFoundException("Order with that id does not exist. Please verify id and try again.");
        }
        }

    @Override
    public OrderReturnedToOwner getOrderByUid(String orderUid) {
        System.out.println("service");
        Orders order = ordersRepository.findByOrderUid(orderUid);
        if (order == null){
            throw new EntityNotFoundException("No order found with that UID. Please verify and try again.");
        }else {
            return ownersGetOrderDtoConverter(order);
        }
    }

    @Override
    public List<OrderReturnedToOwner> getOpenOrderByCustomer(String customer) {
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
        List<OrderReturnedToOwner> openOrders = new ArrayList<>();
        for (Orders order: orders)
            if (order.getClosed().equals("no")) {
            openOrders.add(ownersGetOrderDtoConverter(order));
            }
        if (openOrders.size() > 0) {
            return openOrders;
        }else{
            throw new EntityNotFoundException("No open orders for customer found");
        }
    }
@Transactional
    @Override
    public OrderReturnedToOwner orderReady(Integer orderId) {
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
        return ownersGetOrderDtoConverter(order);
    }
@Transactional
    @Override
    public OrderReturnedToOwner closeOrder(Integer orderId) {
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
        String timeClosed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        order.setClosed(timeClosed);
//        check against customer to see if there are other open orders and if not delete customer
//        Customer customer = customerRepository.getReferenceById(order.getCustomerId());
    Customer customer = customerRepository.getReferenceById(order.getCustomerId());
        List<Orders> customerOrders = customer.getOrders();
        List<Orders> openOrders = new ArrayList<>();
        for (Orders customerOrder : customerOrders) {
            if (customerOrder.getClosed().equals("open")){
                openOrders.add(customerOrder);
            }
        }
        if (openOrders.isEmpty()){
            customerRepository.deleteById(customer.getCustomerId());
        }
        return ownersGetOrderDtoConverter(order);
    }
@Transactional
    @Override
    public String deleteOrder(Integer orderId) {
        System.out.println("service");
        try {
            Orders order = ordersRepository.getReferenceById(orderId);
            System.out.println(order);
        }catch (Exception e){
            throw new EntityNotFoundException("Can not delete order. Verify order id.");
        }
        ordersRepository.deleteById(orderId);
        System.out.println("Order deleted");
        return "Order deleted.";
    }

    @Override
    public String addToOrder(Integer orderId, Integer menuItemId, Integer quantity) {
        System.out.println("service");
        Optional<MenuItem> menuItem;
        Optional<Orders> orderToUpdate;
        try{
            menuItem = Optional.of(menuItemRepository.getReferenceById(menuItemId));
//            System.out.println(menuItem);
        }catch (Exception e){
            throw new EntityNotFoundException("Menu item can not be added to order. Verify menu item id.");
        }
        try{
            orderToUpdate = Optional.of(ordersRepository.getReferenceById(orderId));
//            System.out.println(orderToUpdate);
        }catch (Exception e){
            throw new EntityNotFoundException("Menu item can not be added to order. Verify order id.");
        }


        OrderItem orderItem = OrderItem.builder()
                .item(menuItemRepository.getReferenceById(menuItemId))
                .quantity(quantity)
                .order(ordersRepository.getReferenceById(orderId)).build();
        orderItem.setTotal(menuItemRepository.getReferenceById(menuItemId).getUnitPrice() *
                orderItem.getQuantity());
        orderItemRepository.save(orderItem);

//        update order total
        Orders order  = ordersRepository.getReferenceById(orderId);
        order.setOrderTotal(order.getOrderTotal() + (menuItemRepository.getReferenceById(menuItemId).getUnitPrice() *
                quantity));
        ordersRepository.save(order);
//        System.out.println("Item added to order");
        return  menuItem.get().getItemName() + " x " + quantity + " added to order.";
    }

    @Transactional
    @Override
    public String updateOrderItemQuantity(Integer orderId, Integer orderItemId, Integer newQuantity) {
        System.out.println("service");
        Orders order = ordersRepository.getById(orderId);
        if (order == null){
            throw new EntityNotFoundException("Order item not updated. Verify order exists.");
        }
        OrderItem orderItem = orderItemRepository.getByOrderItemId(orderItemId);
        if (orderItem == null){
            throw new EntityNotFoundException("Order item not updated. Verify order item is part of order.");
        }
        String response;
//        System.out.println("new quantity: " + newQuantity);
            if (newQuantity > 10) {
//                System.out.println("quantity more than 10");
                throw new IllegalArgumentException("We were unable to process your request. " +
                        "Please contact us when trying to order more than 10 of any given item.");
            }
        if(newQuantity == 0){
//                System.out.println("order item: " + orderItem);
//                System.out.println("old order: " + order.getOrderItems());
                orderItemRepository.delete(orderItem);
            order.setOrderTotal(order.getOrderTotal() - orderItem.getTotal());

//                System.out.println("new order: " + order.getOrderItems());
                response = "Item quantity updated, item removed, cart updated.";
            }else{
            orderItem.setQuantity(newQuantity);
            orderItem.setTotal(menuItemRepository.getReferenceById(orderItem.getItem().getId()).getUnitPrice() *
                    orderItem.getQuantity());
//            System.out.println("old total: " + ordersRepository.getReferenceById(orderId).getOrderTotal());
            order.setOrderTotal(order.getOrderTotal() + orderItem.getTotal());
            orderItemRepository.save(orderItem);
            ordersRepository.save(order);
//            System.out.println("new total: " + ordersRepository.getReferenceById(orderId).getOrderTotal());
            response = "Item quantity updated, cart updated.";
        }
//        System.out.println(response);
        return response;
    }

// MARK ALL AS PRIVATE
//    Ensure date time formatter is returning correctly
    @Transactional
    @Override
    public DailySales todaysSales() {
        System.out.println("service");
        DailySales salesToday = new DailySales();
        String formattedSales;
        LocalDate todaysDate = LocalDate.now();
        LocalDate dbDate;
//        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("E dd MMM yyyy");
        DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd MMM yyyy");
        Double salesTotal = 0.00;
        List<Orders> todaysOrders = new ArrayList<>();


        List<Orders> completedOrders = ordersRepository.findByClosed();
        for (Orders completedOrder: completedOrders){
            dbDate = completedOrder.getCreated().toLocalDateTime().toLocalDate();
            if(todaysDate.format(formattedDate).equals(dbDate.format(formattedDate))){
                todaysOrders.add(completedOrder);
            }
        }
//        System.out.println("todays orders:" + todaysOrders);
//                create jpa query that takes today's date and is closed
        for (Orders order:todaysOrders){
            salesTotal += order.getOrderTotal();
        }
        int numberOfOrders = todaysOrders.size();
        salesToday.setDate(todaysDate);
        salesToday.setNumberOfSales(todaysOrders.size());
        salesToday.setTotal(salesTotal);

        formattedSales = "For: " + todaysDate.format(formattedDate) + ", Number of sales: " + numberOfOrders + ", " +
                "Totaling: $" + salesTotal;
        System.out.println(formattedSales);
        return salesToday;
    }

    private OrderReturnedToOwner ownersGetOrderDtoConverter(Orders order) {
        OrderReturnedToOwner orderReturnedToOwner = new OrderReturnedToOwner();
//        set the dto
        orderReturnedToOwner.setOrderId(order.getOrderId());
        if (order.getCustomerId() != null) {
            orderReturnedToOwner.setName(customerRepository.getReferenceById(order.getCustomerId()).getName());
            orderReturnedToOwner.setEmail(customerRepository.getReferenceById(order.getCustomerId()).getEmail());
            orderReturnedToOwner.setPhone(customerRepository.getReferenceById(order.getCustomerId()).getPhoneNumber());
        }
        orderReturnedToOwner.setOrderUid(order.getOrderUid());

//        set the get order items dto
        List<OrderItemReturnedToOwner> orderItemReturnedToOwners = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItems();

        orderItems.forEach(orderItem -> orderItemReturnedToOwners.add(ownersOrderItemDtoConvertor(orderItem)));

        orderReturnedToOwner.setOrderItems(orderItemReturnedToOwners);
        orderReturnedToOwner.setOrderTotal(order.getOrderTotal());
        orderReturnedToOwner.setCreated(order.getCreated());
        orderReturnedToOwner.setReady(order.getReady());
        orderReturnedToOwner.setClosed(order.getClosed());
        return orderReturnedToOwner;
    }

    private OrderItemReturnedToOwner ownersOrderItemDtoConvertor(OrderItem orderItem){
        OrderItemReturnedToOwner orderItemReturnedToOwner = new OrderItemReturnedToOwner();

        orderItemReturnedToOwner.setOrderItemId(orderItem.getOrderItemId());
        orderItemReturnedToOwner.setItemName(orderItem.getItem().getItemName());
        orderItemReturnedToOwner.setQuantity(orderItem.getQuantity());
        orderItemReturnedToOwner.setTotal(orderItem.getTotal());
        return orderItemReturnedToOwner;
    }
}
