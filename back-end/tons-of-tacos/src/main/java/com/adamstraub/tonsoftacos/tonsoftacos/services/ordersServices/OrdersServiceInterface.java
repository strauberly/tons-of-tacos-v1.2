package com.adamstraub.tonsoftacos.tonsoftacos.services.ordersServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.NewOrder;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto.OrderReturnedToCustomer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrdersServiceInterface {

    OrderReturnedToCustomer createOrder(@RequestBody NewOrder order) throws Exception;
}