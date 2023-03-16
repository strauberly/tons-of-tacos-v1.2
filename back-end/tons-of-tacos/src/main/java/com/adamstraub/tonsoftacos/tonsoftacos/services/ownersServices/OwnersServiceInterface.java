package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.OwnersGetOrderDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OwnersServiceInterface {

    @Transactional(readOnly = true)
    List<OwnersGetOrderDto> getAllOrders();
}
