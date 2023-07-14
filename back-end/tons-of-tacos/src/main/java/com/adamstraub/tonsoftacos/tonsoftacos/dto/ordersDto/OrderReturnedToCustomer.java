package com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.ReturnedOrderItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderReturnedToCustomer {
    private String customerName;
    private String orderUid;
    private List<ReturnedOrderItem> orderItems;
    private Double orderTotal;

}
