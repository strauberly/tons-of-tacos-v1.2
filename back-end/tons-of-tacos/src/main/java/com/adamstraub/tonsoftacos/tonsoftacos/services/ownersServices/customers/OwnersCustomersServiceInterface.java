package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.customers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.BusinessReturnedCustomer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnersCustomersServiceInterface {

    @Transactional(readOnly = true)
    List<BusinessReturnedCustomer> getAllCustomers();

//    @Transactional(readOnly = true)
//    OwnersGetCustomerDto getCustomerByName(String name);
BusinessReturnedCustomer getCustomerByName(String name) throws Exception;

    @Transactional(readOnly = true)
    BusinessReturnedCustomer getCustomerById(Integer customerId);

    @Transactional
    String updateCustomerName(Integer customerId, String newCustomerName);

    @Transactional
    String updateCustomerEmail(Integer customerId, String newCustomerEmail);

    @Transactional
    String updateCustomerPhone(Integer customerId, String newCustomerPhone);

    // Successful test written 30 Mar 2023
    @Transactional
    String deleteCustomer(Integer customerId);

}
