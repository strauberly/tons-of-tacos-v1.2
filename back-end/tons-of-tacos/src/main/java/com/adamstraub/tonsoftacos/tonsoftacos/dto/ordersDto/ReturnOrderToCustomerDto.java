package com.adamstraub.tonsoftacos.tonsoftacos.dto.ordersDto;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import lombok.Data;

import java.util.List;

@Data
public class ReturnOrderToCustomerDto {
    private String customerName;
    private String orderUid;
    private List<GetOrderItemDto> orderItems;
    private Double orderTotal;

}
