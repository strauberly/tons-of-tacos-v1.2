package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.customers;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetCustomerDto;
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
public class OwnersCustomersService implements OwnersCustomersServiceInterface {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;


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
        Customer customer = customerRepository.findByName(name);
        return ownersCustomerDtoConvertor(customer);
    }
//@Transactional
    @Override
    public OwnersGetCustomerDto getCustomerById(Integer customerId) {
        System.out.println("service");
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

//    private OwnersGetOrderDto ownersGetOrderDtoConverter(Orders order) {
//        OwnersGetOrderDto ownersGetOrderDto = new OwnersGetOrderDto();
////        set the dto
//        ownersGetOrderDto.setOrderId(order.getOrderId());
//        if (order.getCustomerId() != null) {
//            ownersGetOrderDto.setName(customerRepository.getReferenceById(order.getCustomerId()).getName());
//            ownersGetOrderDto.setEmail(customerRepository.getReferenceById(order.getCustomerId()).getEmail());
//            ownersGetOrderDto.setPhone(customerRepository.getReferenceById(order.getCustomerId()).getPhoneNumber());
//        }
//        ownersGetOrderDto.setOrderUid(order.getOrderUid());
//
//
////        set the get order items dto
//        List<OwnersOrderItemDto> ownersOrderItemDtos = new ArrayList<>();
//        List<OrderItem> orderItems = order.getOrderItems();
//
//        orderItems.forEach(orderItem -> ownersOrderItemDtos.add(ownersOrderItemDtoConvertor(orderItem)));
//        ownersGetOrderDto.setOrderItems(ownersOrderItemDtos);
////
//        ownersGetOrderDto.setOrderTotal(order.getOrderTotal());
//        ownersGetOrderDto.setCreated(order.getCreated());
//        ownersGetOrderDto.setReady(order.getReady());
//        ownersGetOrderDto.setClosed(order.getClosed());
////        System.out.println(ownersGetOrderDto);
//        return ownersGetOrderDto;
//    }
//
//    private OwnersOrderItemDto ownersOrderItemDtoConvertor(OrderItem orderItem){
//        OwnersOrderItemDto ownersOrderItemDto = new OwnersOrderItemDto();
//
//        ownersOrderItemDto.setOrderItemId(orderItem.getOrderItemId());
//        ownersOrderItemDto.setItemName(orderItem.getItemId().getItemName());
//        ownersOrderItemDto.setQuantity(orderItem.getQuantity());
//        ownersOrderItemDto.setTotal(orderItem.getTotal());
//
////        System.out.println(ownersOrderItemDto);
//        return ownersOrderItemDto;
//    }
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
        System.out.println(ownersCustomerDto);
        return ownersCustomerDto;
    }
}
