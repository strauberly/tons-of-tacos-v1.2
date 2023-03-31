package com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class OwnersGetOrderDto {
    private Integer orderId;
    private String name = null;
    private String email = null;
    private String phone = null;
    private String orderUid;
    private List<OwnersOrderItemDto> orderItems;
    private Double orderTotal;
    private Timestamp created;
    private String ready;
    private String status;
}
