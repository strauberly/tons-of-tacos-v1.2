package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.customers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OwnersCustomersServiceInterface {

    @Transactional(readOnly = true)
    List<OwnersGetCustomerDto> getAllCustomers();

//    @Transactional(readOnly = true)
//    OwnersGetCustomerDto getCustomerByName(String name);
OwnersGetCustomerDto getCustomerByName(String name) throws Exception;

    @Transactional(readOnly = true)
    OwnersGetCustomerDto getCustomerById(Integer customerId);

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
