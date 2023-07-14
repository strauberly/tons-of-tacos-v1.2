package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.customers;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.CustomerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.BusinessReturnedCustomer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Customer;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
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

//implement try catch ie(try and catch rest client exception or just exception throw new exception)
    @Override
    public List<BusinessReturnedCustomer> getAllCustomers() {
        System.out.println("service");
//        try {
            List<Customer> customers = customerRepository.findAll();
        if (customers.size() == 0){
            throw new EntityNotFoundException("No customers found. Verify database integrity.");
        }else {
            List<BusinessReturnedCustomer> allCustomersDto = new ArrayList<>();
            customers.forEach(customer -> allCustomersDto.add(ownersCustomerDtoConvertor(customer)));
            System.out.println(allCustomersDto);

            return allCustomersDto;
//        } catch (Exception e){
//            System.out.println(e.getLocalizedMessage());
//            throw new EntityNotFoundException("No customers found.");
        }
    }

    @Override
    public BusinessReturnedCustomer getCustomerByName(String name) {
        System.out.println("service");
        try {
            Customer customer = customerRepository.findByName(name);
            return ownersCustomerDtoConvertor(customer);
//        System.out.println("customer dto: " + customerDto);
//            if (customerDto.getCustomerName() != null){
//                return customerDto;
//            }   else
//                throw new EntityNotFoundException("no such customer");
//            if (customer.getCustomerId() == null) {
//                throw new EntityNotFoundException("no such customer.");
//            } else


        }catch (Exception e){
//            System.out.println(e.getMessage());
            throw new EntityNotFoundException("No customer found by that name. Please check your spelling" +
                    " and formatting.");
        }
    }

//    @Override
//    public OwnersGetCustomerDto getCustomerByName(String name) {
//        System.out.println("service");
//        try {
//            Customer customer = customerRepository.findByName(name);
//            if (customer.getCustomerId() == null ){
//                throw new Exception("no such customer.");
//            }
//            return ownersCustomerDtoConvertor(customer);
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//            throw new EntityNotFoundException("no such customer");
//        }
//    }

    @Transactional(readOnly = true)
    @Override
    public BusinessReturnedCustomer getCustomerById(Integer customerId) {

        System.out.println("service");
    try{

        Customer customer = customerRepository.getById(customerId);
//            if (customer.getCustomerId() == null){
//
//            }else{
                return ownersCustomerDtoConvertor(customer);
        }catch (Exception e) {
//            System.out.println(e.getMessage());
            throw new EntityNotFoundException("Customer with that id not found.");
        }
    }

    @Override
    public String updateCustomerName(Integer customerId, String newCustomerName) {
        System.out.println("service");
// if customer name same as old, if name doesnt contain at least one space, if either name less than 2 or
// greater than 14 or contains chars-1-0 and then well go ahead and cap whatever is put in
        Customer customer;
//        byte[] nameChars = newCustomerName.getBytes(StandardCharsets.UTF_8);
//        int spaces = 0;
//        boolean customerNameValid = true;
        try{
             customer = customerRepository.getById(customerId);
        } catch (Exception e){
            throw new EntityNotFoundException("No customer with that id found.");
        }
//            Customer customer = new Customer();
            byte[] nameChars = newCustomerName.getBytes(StandardCharsets.UTF_8);
            int spaces = 0;
            boolean customerNameValid = true;
        String oldName = customer.getName();
//        if (customer.getCustomerId() == null){
//            throw new EntityNotFoundException("No customer by that id found.");
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
        return "Previous customer name: " + oldName + ", Updated customer name: " + customer.getName();
    }

    @Override
    public String updateCustomerEmail(Integer customerId, String newCustomerEmail) {
        System.out.println("update email service");
        Customer customer;
        try{
            customer = customerRepository.getById(customerId);
        System.out.println("customer: " + customer);
        } catch (Exception e) {
            throw new EntityNotFoundException("No customer with that id found.");
        }
            String oldEmail = customer.getEmail();
            boolean newCustomerEmailValid = true;
        if (oldEmail.equals(newCustomerEmail)) {
            throw new IllegalArgumentException("New customer email can not be same as previous.");
        }
        if (!newCustomerEmail.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,}")) {
//            newCustomerEmailValid = false;
            throw new IllegalArgumentException("Email does not match formatting requirements, please consult the docs.");
        }
//        try {
            if (newCustomerEmailValid) {
                customer.setEmail(newCustomerEmail);
                customerRepository.save(customer);
            }
//        }catch (Exception e){
//            System.out.println(e.getLocalizedMessage());
//            throw new EntityNotFoundException("huh");
//        }
        return "Previous customer email: " + oldEmail + ", Updated customer email: " + customer.getEmail();
//        return "Previous customer email: " + ", Updated customer email: ";
//        } catch (Exception e){
//            throw new EntityNotFoundException("No customer with that id found.");
//        }
    }
    @Transactional
    @Override
    public String updateCustomerPhone(Integer customerId, String newCustomerPhone) {
        System.out.println("update phone service");
            Customer customer;
        boolean newCustomerPhoneNumberValid = false;
        try{
            customer = customerRepository.getById(customerId);
            System.out.println("customer: " + customer);
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

//         = newCustomerPhone.matches("[0-9-]*")
//                && newCustomerPhone.charAt(3) == (char) 46
//                && newCustomerPhone.charAt(7) == (char) 46
//                && newCustomerPhone.length() == 12;
        System.out.println("new number valid: " + newCustomerPhoneNumberValid);
        System.out.println(newCustomerPhone.charAt(3) == (char) 46);
        System.out.println(newCustomerPhone.charAt(7) == (char) 46);
        System.out.println(newCustomerPhone.length() == 12);
        System.out.println(newCustomerPhone.matches("[0-9.]*"));
        System.out.println(newCustomerPhone.charAt(3));
        System.out.println((char) 46);
        if (newCustomerPhoneNumberValid){
            customer.setPhoneNumber(newCustomerPhone);
            customerRepository.save(customer);
            System.out.println(customerRepository.getReferenceById(customerId));
        }
        return "Previous customer phone number: " + oldCustomerPhone + ", Updated customer phone number: " + customer.getPhoneNumber();
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
        return "Customer details removed.";
        }
    }

//
    private BusinessReturnedCustomer ownersCustomerDtoConvertor(Customer customer){
        BusinessReturnedCustomer ownersCustomerDto = new BusinessReturnedCustomer();

        ownersCustomerDto.setCustomerId(customer.getCustomerId());
        ownersCustomerDto.setName(customer.getName());
        ownersCustomerDto.setEmail(customer.getEmail());
        ownersCustomerDto.setPhone(customer.getPhoneNumber());

//       set orders
        List<Orders> orders = ordersRepository.findByCustomerId(customer.getCustomerId());
        List<Integer> orderIds = new ArrayList<>();
        orders.forEach(order -> orderIds.add(order.getOrderId()));
        ownersCustomerDto.setOrderIds(orderIds);
        return ownersCustomerDto;
    }
}
