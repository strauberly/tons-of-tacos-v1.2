package com.adamstraub.tonsoftacos.controllers.ownersControllers.customers;
import com.adamstraub.tonsoftacos.dto.businessDto.CustomerReturnedToOwner;
import com.adamstraub.tonsoftacos.services.ownersServices.customers.OwnersCustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class OwnersCustomersController implements OwnersCustomersControllerInterface {


    @Autowired
    private OwnersCustomersService ownersCustomersService;


    @Override
    public List<CustomerReturnedToOwner> getAllCustomers() {
        System.out.println("controller");
        return ownersCustomersService.getAllCustomers();
    }

    @Override
//    public CustomerReturnedToOwner getCustomerByName(String name) throws Exception {
    public CustomerReturnedToOwner getCustomerByName(String name){
        System.out.println("controller");
            return ownersCustomersService.getCustomerByName(name);
    }

    @Override
//    public CustomerReturnedToOwner getCustomerById(Integer customerId) {
    public CustomerReturnedToOwner getCustomerByUid(String customerUid) {
        System.out.println("controller");
//        return ownersCustomersService.getCustomerById(customerUid);
        return ownersCustomersService.getCustomerByUid(customerUid);
    }

    @Override
//    public String updateCustomerName(Integer customerId, String newCustomerName) {
    public String updateCustomerName(String customerUid, String newCustomerName) {
        System.out.println("controller");
        return ownersCustomersService.updateCustomerName(customerUid, newCustomerName);
    }

    @Override
//    public String updateCustomerEmail(Integer customerId, String newCustomerEmail) {
    public String updateCustomerEmail(String customerUid, String newCustomerEmail) {
        System.out.println("controller");
        return ownersCustomersService.updateCustomerEmail(customerUid, newCustomerEmail);
    }

    @Override
//    public String updateCustomerPhone(Integer customerId, String newCustomerPhone) {
    public String updateCustomerPhone(String customerUid, String newCustomerPhone) {
        System.out.println("controller");
        return ownersCustomersService.updateCustomerPhone(customerUid, newCustomerPhone);
    }

    @Override
//    public String deleteCustomer(Integer customerId) {
    public String deleteCustomer(String customerUid) {
        System.out.println("controller");
        return ownersCustomersService.deleteCustomer(customerUid);
    }

}
