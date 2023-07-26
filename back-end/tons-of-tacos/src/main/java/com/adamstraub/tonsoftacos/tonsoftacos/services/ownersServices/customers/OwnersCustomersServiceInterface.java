package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.customers;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto.BusinessReturnedCustomer;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnersCustomersServiceInterface {

    List<BusinessReturnedCustomer> getAllCustomers();

    BusinessReturnedCustomer getCustomerByName(String name) throws Exception;

    BusinessReturnedCustomer getCustomerById(Integer customerId);

    String updateCustomerName(Integer customerId, String newCustomerName);

    String updateCustomerEmail(Integer customerId, String newCustomerEmail);

    String updateCustomerPhone(Integer customerId, String newCustomerPhone);

    String deleteCustomer(Integer customerId);

}
