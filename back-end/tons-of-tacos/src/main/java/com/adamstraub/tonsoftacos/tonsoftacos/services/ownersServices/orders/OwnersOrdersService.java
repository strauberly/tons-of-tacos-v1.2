package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.orders;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;


// possibly bust out to different services, 1 pertaining to customers and 1 pertaining to orders

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

//  Successful test written 28 Mar 2023
    @Override
    @Transactional(readOnly = true)
    public List<OwnersGetOrderDto> getAllOrders() {
        System.out.println("service");
        List<OwnersGetOrderDto> orderItemDtos = new ArrayList<>();
        List<Orders> orders = ordersRepository.findAll();
        for (Orders order : orders) {
//            System.out.println(orders);
            orderItemDtos.add(ownersGetOrderDtoConverter(order));
//            System.out.println("orders dto" + orderItemDtos);
        }
//        System.out.println("orders" + orders);
        return orderItemDtos;
    }

    //  Successful test written 29 Mar 2023
    @Override
    public OwnersGetOrderDto getOrderById(Integer orderId) {
        System.out.println("service");
        return ownersGetOrderDtoConverter(ordersRepository.getReferenceById(orderId));
    }


    //  Successful test written 28 Mar 2023
    @Override
    public OwnersGetOrderDto getOrderByUid(String orderUid) {
        System.out.println("service");
//        System.out.println(orderUid);
        Orders order = ordersRepository.findByOrderUid(orderUid);
//        System.out.println(order);
        return ownersGetOrderDtoConverter(order);
    }

    //  Successful test written 28 Mar 2023
    @Override
    public List<OwnersGetOrderDto> getOpenOrderByCustomer(String customer) {
        System.out.println("service");
        Customer customerObj = customerRepository.findByName(customer);
        List<Orders> orders = ordersRepository.findByCustomerId(customerObj.getCustomerId());
        List<OwnersGetOrderDto> openOrders = new ArrayList<>();
        for (Orders order: orders)
            if (order.getClosed().equals("closed")) {
                throw new NoSuchElementException("No open orders for customer found.");
            }else{
            openOrders.add(ownersGetOrderDtoConverter(order));
            }
        return openOrders;
    }

    //  Successful test written 28 Mar 2023
    @Override
    public void orderReady(Integer orderId) {
        System.out.println("service");
        Orders order = ordersRepository.getReferenceById(orderId);
        String timeReady = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        order.setReady(timeReady);
        System.out.println("Order up!");
    }
    //  Successful test written 28 Mar 2023
    @Override
    public void closeOrder(Integer orderId) {
        System.out.println("service");
        Orders order = ordersRepository.getReferenceById(orderId);
        String timeClosed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        order.setClosed(timeClosed);
        System.out.println("Order closed");
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
            System.out.println("All customer orders closed, customer information removed.");
        }
    }

    //  Successful test written 29 Mar 2023
    @Override
    public void deleteOrder(Integer orderId) {
        System.out.println("service");
        ordersRepository.deleteById(orderId);
        System.out.println("Order deleted");
    }

    //  Successful test written 29 Mar 2023
    @Override
    public void addToOrder(Integer orderId, Integer menuItemId, Integer quantity) {
        System.out.println("service");
//        System.out.println(ordersRepository.getReferenceById(orderId));
//        System.out.println("order id: " + orderId);
//        System.out.println("menu item id: " + menuItemId);
//        System.out.println("quantity: " + quantity);
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

    //  Successful test written 29 Mar 2023
    @Override
    public void updateOrderItem(Integer orderId, Integer orderItemId, Integer newQuantity) {
        System.out.println("service");

        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
//        System.out.println(orderItem);
        Orders order = ordersRepository.getReferenceById(orderId);
//        System.out.println(order);
        String response;
        if(newQuantity == 0){
            order.setOrderTotal(order.getOrderTotal() - orderItem.getTotal());
            orderItemRepository.deleteById(orderItemId);
            response = "Item quantity updated, item removed, cart updated.";
        }else{
            orderItem.setQuantity(newQuantity);
            orderItem.setTotal(menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice() *
                    orderItem.getQuantity());
            order.setOrderTotal(order.getOrderTotal() + orderItem.getTotal());
            orderItemRepository.save(orderItem);
            response = "Item quantity updated, cart updated.";
        }
//        System.out.println(order);
        ordersRepository.save(order);
        System.out.println(response);
    }

    //  Successful test written 29 Mar 2023
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
//
        ownersGetOrderDto.setOrderTotal(order.getOrderTotal());
        ownersGetOrderDto.setCreated(order.getCreated());
        ownersGetOrderDto.setReady(order.getReady());
        ownersGetOrderDto.setStatus(order.getClosed());
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
//    private OwnersGetCustomerDto ownersCustomerDtoConvertor(Customer customer){
//        OwnersGetCustomerDto ownersCustomerDto = new OwnersGetCustomerDto();
//
//        ownersCustomerDto.setCustomerId(customer.getCustomerId());
//        ownersCustomerDto.setName(customer.getName());
//        ownersCustomerDto.setEmail(customer.getEmail());
//        ownersCustomerDto.setPhone(customer.getPhoneNumber());
//
////       set orders
//        List<Orders> orders = ordersRepository.findByCustomerId(customer.getCustomerId());
//        List<Integer> orderIds = new ArrayList<>();
//        orders.forEach(order -> orderIds.add(order.getOrderId()));
//        ownersCustomerDto.setOrderIds(orderIds);
//        System.out.println(ownersCustomerDto);
//        return ownersCustomerDto;
//    }
}