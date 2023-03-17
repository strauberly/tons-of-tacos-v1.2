package com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrdersRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class OwnersGetCustomerDto {
//    @Autowired
//    OrdersRepository ordersRepository;
//    Orders orders;
    private Integer customerId;
    private String name;
    private String email;
    private String phone;
    private List<Integer> orderIds;
}
