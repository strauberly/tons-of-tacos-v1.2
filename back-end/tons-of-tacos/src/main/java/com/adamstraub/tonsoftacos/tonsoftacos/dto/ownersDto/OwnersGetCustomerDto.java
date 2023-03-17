package com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto;

import lombok.Data;

import java.util.List;

@Data
public class OwnersGetCustomerDto {
    private Integer customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private List<OwnersGetOrderDto> orders;
}
