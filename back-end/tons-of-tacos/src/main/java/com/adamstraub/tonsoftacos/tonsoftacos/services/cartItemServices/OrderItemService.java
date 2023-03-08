package com.adamstraub.tonsoftacos.tonsoftacos.services.cartItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OrderItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.orderItemsDto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.OrderItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderItemService implements OrderItemServiceInterface {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    @Transactional
    public void addToCart(@RequestBody OrderItem orderItem) throws InvalidPropertiesFormatException {
        System.out.println("service");
//        System.out.println(cartItem);
        orderItem.setCartItemId(0);
        orderItem.setTotal(orderItem.getQuantity() *
                menuItemRepository.getReferenceById(orderItem.getItemId().getId()).getUnitPrice());

//        validation needs to be re-addressed after linking to orders
//
//        if (cartItem.getItemId().getId() > menuItemRepository.count()) {
//            throw new NoSuchElementException("A menu item with that id does not exist.");
//        }
//        if (!cartItem.getItemId().toString().matches(".*\\d.*")) {
//            throw new NumberFormatException("You have entered in invalid menu item id.");
//        }
//        if (!cartItem.getOrder().matches("([0-9\\-]+)")) {
//            throw new InvalidPropertiesFormatException("You have entered an invalid cart id.");
//        }
//        if (!cartItem.getQuantity().toString().matches(".*\\d.*")) {
//            throw new NumberFormatException("You have entered in invalid quantity.");
//        }
//        if (!cartItem.getTotal().equals(cartItem.getTotal().doubleValue())) {
//            throw new NumberFormatException("You have enter an invalid format for total");
//        }
//        System.out.println(orderItem);
        orderItemRepository.save(orderItem);
    }

    @Override
    public List<GetOrderItemDto> findByCartUuid(String cartUuid) {

        System.out.println("service");
        List<OrderItem> orderItems = orderItemRepository.order(cartUuid);
//        System.out.println("orderitems = " + orderItems);
        List<GetOrderItemDto> orderItemDtos = new ArrayList<>();
        if (orderItems.isEmpty()) {
            throw new NoSuchElementException("No cart exists with that id");
        } else {
            for (OrderItem orderItem : orderItems) {
                orderItemDtos.add(getOrderItemDtoConversion(orderItem));
            }
        }
        System.out.println("Retrieved a cart.");
//        System.out.println(orderItemDtos);
        return orderItemDtos;
    }

@Override
@Transactional
public OrderItemDto updateCart(@PathVariable Integer orderItemId, @PathVariable Integer newQuantity) {
    System.out.println("service");
    OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
    MenuItem menuItem = menuItemRepository.getReferenceById(orderItem.getItemId().getId());
    OrderItemDto orderItemDto = null;
    if (newQuantity == 0) {
        orderItemRepository.save(orderItem);
        orderItemRepository.deleteByCartItemId(orderItemId);
        orderItemDto = modelMapper.map(orderItem, OrderItemDto.class);
        System.out.println("Cart updated. Item removed.");
    } else {
        orderItem.setQuantity(newQuantity);
        orderItem.setTotal(orderItem.getQuantity() * menuItem.getUnitPrice());
        orderItemDto = modelMapper.map(orderItem, OrderItemDto.class);
        orderItemRepository.save(orderItem);
        System.out.println("Cart item updated.");
//        System.out.println(orderItemDto);
    }
    orderItemDto.setOrderPk("NA");
    return orderItemDto;
}


    @Override
    @Transactional
    public void removeCartItem(@PathVariable Integer orderItemId) {
        System.out.println("service");
        OrderItem orderItem = orderItemRepository.getReferenceById(orderItemId);
//        System.out.println(orderItem);
        if (orderItem.getItemId() == null) {
            throw new NoSuchElementException("This order id does not exist.");
        } else {
            System.out.println("Cart item removed.");
            orderItemRepository.deleteByCartItemId(Math.toIntExact(orderItem.getCartItemId()));

        }
    }

    private GetOrderItemDto getOrderItemDtoConversion(OrderItem orderItem){
        GetOrderItemDto getOrderItemDto = new GetOrderItemDto();

        getOrderItemDto.setUnitPrice(orderItem.getItemId().getUnitPrice());
        getOrderItemDto.setItemName(orderItem.getItemId().getItemName());
        getOrderItemDto.setTotal(orderItem.getTotal());
        getOrderItemDto.setQuantity(orderItem.getQuantity());
//        System.out.println(getOrderItemDto);
        return getOrderItemDto;
    }
}
