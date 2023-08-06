package com.adamstraub.tonsoftacos.tonsoftacos.dto.customerDto.ordersDto;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.customerDto.orderItemsDto.OrderItemReturnedToCustomer;
import lombok.Data;

import java.util.List;

@Data
public class OrderReturnedToCustomer {
    private String customerName;
    private String orderUid;
    private List<OrderItemReturnedToCustomer> orderItems;
    private Double orderTotal;

}
