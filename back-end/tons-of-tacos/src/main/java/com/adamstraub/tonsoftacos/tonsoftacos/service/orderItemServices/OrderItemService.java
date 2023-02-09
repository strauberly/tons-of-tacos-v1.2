package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderItemService implements OrderItemServiceInterface {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
//    @Transactional
    public OrderItem addToCart(@RequestBody OrderItem orderItem) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println( objectMapper.writeValueAsString(orderItem));
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findByOrderUuid(String orderUuid) {
        return orderItemRepository.findByOrderUuid(orderUuid);
    }

    @Override
    @Transactional
    public OrderItem updateCart(@PathVariable Integer orderItemId, @PathVariable Integer newQuantity) {
        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
        MenuItem menuItem = menuItemRepository.getReferenceById(orderItem.getMenuItemId());
        orderItem.setQuantity(newQuantity);
        orderItem.setTotal(BigDecimal.valueOf(orderItem.getQuantity() * menuItem.getUnit_price()));
        return orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional
    public void removeCartItem(@PathVariable Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
        orderItemRepository.deleteById(orderItem.getId());
    }


}
