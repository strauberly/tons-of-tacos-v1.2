package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.customers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.CustomerReturnedToOwner;

import java.util.List;

public interface OwnersCustomersServiceInterface {

    List<CustomerReturnedToOwner> getAllCustomers();

    CustomerReturnedToOwner getCustomerByName(String name) throws Exception;

    CustomerReturnedToOwner getCustomerById(Integer customerId);

    String updateCustomerName(Integer customerId, String newCustomerName);

    String updateCustomerEmail(Integer customerId, String newCustomerEmail);

    String updateCustomerPhone(Integer customerId, String newCustomerPhone);

    String deleteCustomer(Integer customerId);

}
