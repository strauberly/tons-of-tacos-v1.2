package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityExistsException;
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
    public OrderItem addToCart(OrderItem orderItem) throws InvalidPropertiesFormatException {
        System.out.println("service");
        System.out.println(orderItem);
        if(orderItem.getItemId().getId() > menuItemRepository.count()) {
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
            System.out.println(orderItem);
        return orderItemRepository.save(orderItem);
    }



    @Override
    public List<OrderItem> findByOrderUuid(String orderUuid) {
        System.out.println("service");
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
        System.out.println("service");
        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
        MenuItem menuItem = menuItemRepository.getReferenceById(orderItem.getItemId().getId());

        if(orderItem.getItemId() == null){
            throw new EntityExistsException("That order item does not exist and cannot be updated.");
        }
        if(newQuantity == 0){
            orderItemRepository.save(orderItem);
            removeCartItem(orderItemId);
        } else {
            orderItem.setQuantity(newQuantity);
            orderItem.setTotal(orderItem.getQuantity() * menuItem.getUnitPrice());
            orderItemRepository.save(orderItem);
        }
            return orderItem;
    }
//
    @Override
    @Transactional
    public OrderItem removeCartItem(@PathVariable Integer orderItemId) {
        System.out.println("service");
        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
        if (orderItem.getItemId() == null) {
            throw new NoSuchElementException("This order item does not exist.");
        } else {
            orderItemRepository.deleteById(Math.toIntExact(orderItem.getOrderItemId()));
        }
        return orderItem;
    }
}
