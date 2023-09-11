package com.adamstraub.tonsoftacos.services.ownersServices.customers;

import com.adamstraub.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.dto.businessDto.CustomerReturnedToOwner;
import com.adamstraub.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.entities.Orders;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.*;
@Data
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
    public List<CustomerReturnedToOwner> getAllCustomers() {
        System.out.println("service");

            List<Customer> customers = customerRepository.findAll();
        if (customers.size() == 0){
            throw new EntityNotFoundException("No customers found. Verify database integrity.");
        }else {
            List<CustomerReturnedToOwner> allCustomersDto = new ArrayList<>();
            customers.forEach(customer -> allCustomersDto.add(ownersCustomerDtoConvertor(customer)));
//            System.out.println(allCustomersDto);

            return allCustomersDto;
        }
    }

    @Override
//    public CustomerReturnedToOwner getCustomerByName(String name) {
    public CustomerReturnedToOwner getCustomerByName(String name) {
        System.out.println("service");
//        try {
            Customer customer = customerRepository.findByName(name);
            if (customer == null){
                throw new EntityNotFoundException("No customer found by that name. Please check your spelling" +
                    " and formatting.");
            }else {
                return ownersCustomerDtoConvertor(customer);
            }
//        }catch (Exception e){
//            throw new EntityNotFoundException("No customer found by that name. Please check your spelling" +
//                    " and formatting.");
//        }
    }



    @Override
    public CustomerReturnedToOwner getCustomerByUid(String customerUid) {
//    public CustomerReturnedToOwner getCustomerById(Integer customerId) {

        System.out.println("service");
//    try{
        Customer customer = customerRepository.findByCustomerUid(customerUid);
        if (customer == null) {
            throw new EntityNotFoundException("No customer found with that UID. Please verify and try  again.");
        } else{
            return ownersCustomerDtoConvertor(customer);
    }
//        }catch (Exception e) {
//            throw new EntityNotFoundException("Customer with that id not found.");
//        }
    }

    @Override
    public String updateCustomerName(Integer customerId, String newCustomerName) {
        System.out.println("service");
        Customer customer;
        try{
             customer = customerRepository.getById(customerId);
        } catch (Exception e){
            throw new EntityNotFoundException("No customer with that id found.");
        }
            byte[] nameChars = newCustomerName.getBytes(StandardCharsets.UTF_8);
            int spaces = 0;
            boolean customerNameValid = true;
        String oldName = customer.getName();

        if (oldName.equals(newCustomerName)) {
            throw new IllegalArgumentException("New customer name can not be same as previous name.");
        }
        for (Byte namechar : nameChars) {
            if (Objects.equals(namechar, (byte) 32)) {
                spaces += 1;
            }
        }
//        possibly alter for just ^[a-zA-Z]$+ [a-zA-Z]+. currently accepting letters from any language.
        if (!newCustomerName.matches("^\\p{L}+[\\p{L}\\p{Pd}\\p{Zs}']*\\p{L}+$|^\\p{L}+$") ||
                !(spaces == 1)) {
            customerNameValid = false;
            System.out.println(customerNameValid);
            throw new IllegalArgumentException("Customer name incorrectly formatted. Please consult the documentation.");
        }
        if (customerNameValid) {
            customer.setName(newCustomerName);
            customerRepository.save(customer);
        }
        return "Previous customer name: " + oldName + ", updated to: " + customer.getName() + ".";
    }

    @Override
    public String updateCustomerEmail(Integer customerId, String newCustomerEmail) {
        System.out.println("service");
        Customer customer;
        try{
            customer = customerRepository.getById(customerId);
//        System.out.println("customer: " + customer);
        } catch (Exception e) {
            throw new EntityNotFoundException("No customer with that id found.");
        }
            String oldEmail = customer.getEmail();
            boolean newCustomerEmailValid = true;
        if (oldEmail.equals(newCustomerEmail)) {
            throw new IllegalArgumentException("New customer email can not be same as previous.");
        }
        if (!newCustomerEmail.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}")) {
            throw new IllegalArgumentException("Email does not match formatting requirements, please consult the docs.");
        }
            if (newCustomerEmailValid) {
                customer.setEmail(newCustomerEmail);
                customerRepository.save(customer);
            }
        return "Previous customer email: " + oldEmail + ", updated to: " + customer.getEmail() + ".";
    }
    @Transactional
    @Override
    public String updateCustomerPhone(Integer customerId, String newCustomerPhone) {
        System.out.println("service");
            Customer customer;
        boolean newCustomerPhoneNumberValid = false;
        try{
            customer = customerRepository.getById(customerId);
//            System.out.println("customer: " + customer);
        } catch (Exception e){
            throw new EntityNotFoundException("No customer with that id found.");
        }
        String oldCustomerPhone = customer.getPhoneNumber();
        if (newCustomerPhone.matches("[0-9.]*")
                && newCustomerPhone.charAt(3) == (char) 46
                && newCustomerPhone.charAt(7) == (char) 46
                && newCustomerPhone.length() == 12){
            newCustomerPhoneNumberValid = true;
        }

//        System.out.println("new number valid: " + newCustomerPhoneNumberValid);
//        System.out.println(newCustomerPhone.charAt(3) == (char) 46);
//        System.out.println(newCustomerPhone.charAt(7) == (char) 46);
//        System.out.println(newCustomerPhone.length() == 12);
//        System.out.println(newCustomerPhone.matches("[0-9.]*"));
//        System.out.println(newCustomerPhone.charAt(3));
//        System.out.println((char) 46);
        if (newCustomerPhoneNumberValid){
            customer.setPhoneNumber(newCustomerPhone);
            customerRepository.save(customer);
//            System.out.println(customerRepository.getReferenceById(customerId));
        }
        return "Previous customer phone number: " + oldCustomerPhone + ", updated to: " + customer.getPhoneNumber() + ".";
    }

    @Override
    public String deleteCustomer(Integer customerId) {
        System.out.println("service");
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()){
            throw new EntityNotFoundException("Customer does not exist. Double check the id.");
        }else {
        customerRepository.deleteById(customerId);
        System.out.println("Customer deleted");
        return customer.get().getName() + " removed from application records.";
        }
    }


    private CustomerReturnedToOwner ownersCustomerDtoConvertor(Customer customer){
        CustomerReturnedToOwner ownersCustomerDto = new CustomerReturnedToOwner();
//        ownersCustomerDto.setCustomerId(customer.getCustomerId());
//        ownersCustomerDto.setCustomerId(customer.getCustomerId());
        ownersCustomerDto.setCustomerUid(customer.getCustomerUid());
        ownersCustomerDto.setName(customer.getName());
        ownersCustomerDto.setEmail(customer.getEmail());
        ownersCustomerDto.setPhone(customer.getPhoneNumber());

        List<Orders> orders = ordersRepository.findByCustomerId(customer.getCustomerId());
//        List<Integer> orderIds = new ArrayList<>();
        List<String> orderIds = new ArrayList<>();
//        orders.forEach(order -> orderIds.add(order.getOrderId()));
        orders.forEach(order -> orderIds.add(order.getOrderUid()));
        ownersCustomerDto.setOrderIds(orderIds);
        return ownersCustomerDto;
    }
}
