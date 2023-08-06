package com.adamstraub.tonsoftacos.tonsoftacos.controllers.ownersControllers.customers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.CustomerReturnedToOwner;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.customers.OwnersCustomersService;
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
    public CustomerReturnedToOwner getCustomerByName(String name) throws Exception {
        System.out.println("controller");
            return ownersCustomersService.getCustomerByName(name);
    }

    @Override
    public CustomerReturnedToOwner getCustomerById(Integer customerId) {
        System.out.println("controller");
        return ownersCustomersService.getCustomerById(customerId);
    }

    @Override
    public String updateCustomerName(Integer customerId, String newCustomerName) {
        System.out.println("controller");
        return ownersCustomersService.updateCustomerName(customerId, newCustomerName);
    }

    @Override
    public String updateCustomerEmail(Integer customerId, String newCustomerEmail) {
        System.out.println("controller");
        return ownersCustomersService.updateCustomerEmail(customerId, newCustomerEmail);
    }

    @Override
    public String updateCustomerPhone(Integer customerId, String newCustomerPhone) {
        System.out.println("controller");
        return ownersCustomersService.updateCustomerPhone(customerId, newCustomerPhone);
    }

    @Override
    public String deleteCustomer(Integer customerId) {
        System.out.println("controller");
        return ownersCustomersService.deleteCustomer(customerId);
    }

}
