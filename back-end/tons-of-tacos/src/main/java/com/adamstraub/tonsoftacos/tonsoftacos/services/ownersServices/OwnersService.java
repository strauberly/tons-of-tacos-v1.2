package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.GetOrdersDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.orderItemServices.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

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

    @Override
    public OwnersGetOrderDto getOrderByUid(String orderUid) {
        System.out.println("service");
        System.out.println(orderUid);
        Orders order = ordersRepository.findByOrderUid(orderUid);
        System.out.println(order);
        return ownersGetOrderDtoConverter(order);
    }

    @Override
    public OwnersGetOrderDto getOpenOrderByCustomer(String customer) {
        System.out.println("service");
        //        get by customer id where customer name = customer
//        System.out.println(customer);
        Customer customerObj = customerRepository.findByName(customer);
//        System.out.println(customerObj);
        List<Orders> orders = ordersRepository.findByCustomerId(customerObj.getCustomerId());
        Orders openOrder = null;
        for (Orders order: orders)
            if (order.getStatus().equals("closed")) {
                throw new NoSuchElementException("No open orders for customer found.");
            }else{
                openOrder = order;
            }
        return ownersGetOrderDtoConverter(openOrder);
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


        //create jpa query that searches status closed *done
//        get timestamp java.sql and convert to day only *done
//        compare todays date do date for completed orders *done
//        if == add to new list then get those totals and add up * done
        List<Orders> completedOrders = ordersRepository.findByStatus("closed");
        for (Orders completedOrder: completedOrders){
            dbDate = completedOrder.getCreated().toLocalDateTime().toLocalDate();
            if(todaysDate.format(formattedDate).equals(dbDate.format(formattedDate))){
                todaysOrders.add(completedOrder);
            }
//            for (Orders order:todaysOrders){
//                salesTotal += order.getOrderTotal();
//            }
//            System.out.println(todaysDate.format(formattedDate).equals(dbDate.format(formattedDate)));
//            System.out.println("string value db time: " + dbDate);
        }
//                create jpa query that takes todays date and status closed
        for (Orders order:todaysOrders){
            salesTotal += order.getOrderTotal();
        }
//        System.out.println(salesTotal);
//        System.out.println(todaysOrders.size());
//        String numberOfOrders = String.valueOf(Math.toIntExact(ordersRepository.count()));
//        Todays date
        String numberOfOrders = String.valueOf(todaysOrders.size());
        formattedSales = "For: " + todaysDate.format(formattedDate) + ", Number of sales: " + numberOfOrders + ", " +
                "Totaling: $" + salesTotal;
//        System.out.println(completedOrders);
        return formattedSales;
    }

    @Override
    public void foodReady(Integer orderId) {
        System.out.println("service");
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

    @Override
    public void deleteOrder(Integer orderId) {
        System.out.println("service");
        ordersRepository.deleteById(orderId);
        System.out.println("Order deleted");
    }

    @Override
    public List<OwnersGetCustomerDto> getAllCustomers() {
        System.out.println("service");
        List<Customer> customers = customerRepository.findAll();
        List<OwnersGetCustomerDto> allCustomersDto = new ArrayList<>();
        customers.forEach(customer -> allCustomersDto.add(ownersCustomerDtoConvertor(customer)));
        System.out.println(allCustomersDto);
        return allCustomersDto;
    }

    @Override
    public OwnersGetCustomerDto getCustomerByName(String name) {
        System.out.println("service");
//        OwnersGetCustomerDto customerDto;
        Customer customer = customerRepository.findByName(name);
        return ownersCustomerDtoConvertor(customer);
    }

    @Override
    public OwnersGetCustomerDto getCustomerById(Integer customerId) {
        System.out.println("service");
//        OwnersGetCustomerDto customerDto;
        Customer customer = customerRepository.getById(customerId);
        return ownersCustomerDtoConvertor(customer);
    }

    @Override
    public String updateCustomerName(Integer customerId, String newCustomerName) {
        System.out.println("service");
        Customer customer = customerRepository.getById(customerId);
        String oldName = customer.getName();
        customer.setName(newCustomerName);
        customerRepository.save(customer);
        return "Previous customer name: " + oldName + ", Updated customer name: " + customer.getName();
    }

    @Override
    public String updateCustomerEmail(Integer customerId, String newCustomerEmail) {
        System.out.println("service");
        Customer customer = customerRepository.getById(customerId);
        String oldEmail = customer.getEmail();
        customer.setEmail(newCustomerEmail);
        customerRepository.save(customer);
        return "Previous customer email: " + oldEmail + ", Updated customer email: " + customer.getEmail();
    }

    @Override
    public String updateCustomerPhone(Integer customerId, String newCustomerPhone) {
        System.out.println("service");
        Customer customer = customerRepository.getById(customerId);
        String oldCustomerPhone = customer.getPhoneNumber();
        customer.setPhoneNumber(newCustomerPhone);
        customerRepository.save(customer);
        return "Previous customer phone number: " + oldCustomerPhone + ", Updated customer phone number: " + customer.getPhoneNumber();
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        System.out.println("service");

        customerRepository.deleteById(customerId);
        System.out.println("Customer deleted");
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
    private OwnersGetCustomerDto ownersCustomerDtoConvertor(Customer customer){
        OwnersGetCustomerDto ownersCustomerDto = new OwnersGetCustomerDto();
        ownersCustomerDto.setCustomerId(customer.getCustomerId());
        ownersCustomerDto.setName(customer.getName());
        ownersCustomerDto.setEmail(customer.getEmail());
        ownersCustomerDto.setPhone(customer.getPhoneNumber());

//       set orders
        List<Orders> orders = ordersRepository.findByCustomerId(customer.getCustomerId());
        List<Integer> orderIds = new ArrayList<>();
        orders.forEach(order -> orderIds.add(order.getOrderId()));
        ownersCustomerDto.setOrderIds(orderIds);
//
//        List<OwnersGetOrderDto> ownersGetOrderDto = new ArrayList<>();
//        orders.forEach(order -> ownersGetOrderDto.add(ownersGetOrderDtoConverter(order)));
//        ownersCustomerDto.setOrders(ownersGetOrderDto);



//        ownersOrderItemDto.setOrderItemId(orderItem.getOrderItemId());
//        ownersOrderItemDto.setItemName(orderItem.getItemId().getItemName());
//        ownersOrderItemDto.setQuantity(orderItem.getQuantity());
//        ownersOrderItemDto.setTotal(orderItem.getTotal());


//        ownersOrderItemDto.setUnitPrice(orderItem.getItemId().getUnitPrice());
//        ownersOrderItemDto.setItemName(orderItem.getItemId().getItemName());
//        ownersOrderItemDto.setTotal(orderItem.getTotal());
//        ownersOrderItemDto.setQuantity(orderItem.getQuantity());
        System.out.println(ownersCustomerDto);
        return ownersCustomerDto;
    }
}
