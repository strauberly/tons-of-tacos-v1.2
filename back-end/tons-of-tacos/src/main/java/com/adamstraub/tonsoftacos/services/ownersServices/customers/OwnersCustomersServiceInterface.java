package com.adamstraub.tonsoftacos.services.ownersServices.customers;
import com.adamstraub.tonsoftacos.dto.businessDto.CustomerReturnedToOwner;

import java.util.List;


public interface OwnersCustomersServiceInterface {

    List<CustomerReturnedToOwner> getAllCustomers();

    CustomerReturnedToOwner getCustomerByName(String name);

//    CustomerReturnedToOwner getCustomerById(Integer customerId);
CustomerReturnedToOwner getCustomerByUid(String customerUid);
//    String updateCustomerName(Integer customerId, String newCustomerName);
String updateCustomerName(String customerUid, String newCustomerName);
//    String updateCustomerEmail(Integer customerId, String newCustomerEmail);
String updateCustomerEmail(String customerUid, String newCustomerEmail);

//    String updateCustomerPhone(Integer customerId, String newCustomerPhone);
String updateCustomerPhone(String customerUid, String newCustomerPhone);
//    String deleteCustomer(Integer customerId);
String deleteCustomer(String customerUid);
}
