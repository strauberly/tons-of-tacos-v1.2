package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityExistsException;
import java.math.BigDecimal;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderItemService implements OrderItemServiceInterface {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
   @Transactional
    public OrderItem addToCart(@RequestBody OrderItem orderItem) throws InvalidPropertiesFormatException {
        if(orderItem.getItemId()>menuItemRepository.count()) {
            throw new NoSuchElementException("A menu item with that id does not exist.");
        }if (!orderItem.getItemId().toString().matches(".*\\d.*")) {
            throw new NumberFormatException("You have entered in invalid menu item id.");
        }if(!orderItem.getOrderUuid().matches("([0-9\\-]+)")) {
            throw new InvalidPropertiesFormatException("You have entered an invalid cart id.");
        }if(!orderItem.getQuantity().toString().matches(".*\\d.*")) {
            throw new NumberFormatException("You have entered in invalid quantity.");
        }if(!orderItem.getTotal().equals(orderItem.getTotal().doubleValue())){
        throw new NumberFormatException("You have enter an invalid format for total");
        }else
        return orderItemRepository.save(orderItem);
    }

    @Override
    public List<OrderItem> findByOrderUuid(String orderUuid) {
        List<OrderItem>orderItems = orderItemRepository.orderUuid(orderUuid);
        if(orderItems.isEmpty()){
            throw new NoSuchElementException("No cart exists with that id");
        }
        return orderItemRepository.orderUuid(orderUuid);
    }
//
    @Override
    @Transactional
    public OrderItem updateCart(@PathVariable Integer orderItemId, @PathVariable Integer newQuantity) {
        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
        if(orderItem.getItemId() == null){
            throw new EntityExistsException("That order item does not exist and cannot be updated.");
        }else {
            MenuItem menuItem = menuItemRepository.getReferenceById(orderItem.getItemId());
            orderItem.setQuantity(newQuantity);
            orderItem.setTotal(orderItem.getQuantity() * menuItem.getUnitPrice());
            return orderItemRepository.save(orderItem);
        }
    }
//
    @Override
    @Transactional
    public void removeCartItem(@PathVariable Integer orderItemId) {
        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
        orderItemRepository.deleteById(Math.toIntExact(orderItem.getOrderItemId()));
    }
//

}
