package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetCustomerDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OwnersServiceInterface {

    @Transactional(readOnly = true)
    List<OwnersGetOrderDto> getAllOrders();
    @Transactional(readOnly = true)
    OwnersGetOrderDto getOrderByUid(@PathVariable String orderUid);
    @Transactional
    OwnersGetOrderDto getOpenOrderByCustomer(String customer);
    @Transactional
    String todaysSales();
    @Transactional
    void foodReady(Integer orderId);
    @Transactional
    void closeOrder(Integer orderId);
    @Transactional
    void deleteOrder(Integer orderId);
    @Transactional(readOnly = true)
    List<OwnersGetCustomerDto> getAllCustomers();
    @Transactional(readOnly = true)
    OwnersGetCustomerDto getCustomerByName(String name);
    @Transactional(readOnly = true)
    OwnersGetCustomerDto getCustomerById(Integer customerId);
    @Transactional
    String updateCustomerName(Integer customerId, String newCustomerName);
    @Transactional
    String updateCustomerEmail(Integer customerId, String newCustomerEmail);
    @Transactional
    String updateCustomerPhone(Integer customerId, String newCustomerPhone);
    @Transactional
    void deleteCustomer(Integer customerId);
}
