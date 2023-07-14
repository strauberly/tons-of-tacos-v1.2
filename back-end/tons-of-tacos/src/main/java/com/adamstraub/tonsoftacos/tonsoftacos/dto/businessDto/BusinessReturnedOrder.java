package com.adamstraub.tonsoftacos.tonsoftacos.dto.businessDto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class BusinessReturnedOrder {
    private Integer orderId;
    private String name = null;
    private String email = null;
    private String phone = null;
    private String orderUid;
    private List<BusinessOrderItem> orderItems;
    private Double orderTotal;
    private Timestamp created;
    private String ready;
    private String closed;
}
