package com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices;

import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.CartItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.MenuItem;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.CartItem;
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
public class CartItemService implements CartItemServiceInterface {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Override
    @Transactional
    public void addToCart(@RequestBody CartItem cartItem) throws InvalidPropertiesFormatException {
        System.out.println("service");
        cartItem.setOrderItemId(0);
        cartItem.setTotal(cartItem.getQuantity() *
                menuItemRepository.getReferenceById(cartItem.getItemId().getId()).getUnitPrice());

        if (cartItem.getItemId().getId() > menuItemRepository.count()) {
            throw new NoSuchElementException("A menu item with that id does not exist.");
        }
        if (!cartItem.getItemId().toString().matches(".*\\d.*")) {
            throw new NumberFormatException("You have entered in invalid menu item id.");
        }
        if (!cartItem.getCartUuid().matches("([0-9\\-]+)")) {
            throw new InvalidPropertiesFormatException("You have entered an invalid cart id.");
        }
        if (!cartItem.getQuantity().toString().matches(".*\\d.*")) {
            throw new NumberFormatException("You have entered in invalid quantity.");
        }
        if (!cartItem.getTotal().equals(cartItem.getTotal().doubleValue())) {
            throw new NumberFormatException("You have enter an invalid format for total");
        }
//        System.out.println(orderItem);
        cartItemRepository.save(cartItem);
    }

    @Override
    public List<GetOrderItemDto> findByCartUuid(String cartUuid) {

        System.out.println("service");
        List<CartItem> cartItems = cartItemRepository.cartUuid(cartUuid);
//        System.out.println("orderitems = " + orderItems);
        List<GetOrderItemDto> orderItemDtos = new ArrayList<>();
        if (cartItems.isEmpty()) {
            throw new NoSuchElementException("No cart exists with that id");
        } else {
            for (CartItem cartItem : cartItems) {
                orderItemDtos.add(getOrderItemDtoConversion(cartItem));
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
    CartItem cartItem = cartItemRepository.getReferenceById(orderItemId);
    MenuItem menuItem = menuItemRepository.getReferenceById(cartItem.getItemId().getId());
    OrderItemDto orderItemDto = null;
    if (newQuantity == 0) {
        cartItemRepository.save(cartItem);
        cartItemRepository.deleteByOrderItemId(orderItemId);
        orderItemDto = modelMapper.map(cartItem, OrderItemDto.class);
        System.out.println("Cart updated. Item removed.");
    } else {
        cartItem.setQuantity(newQuantity);
        cartItem.setTotal(cartItem.getQuantity() * menuItem.getUnitPrice());
        orderItemDto = modelMapper.map(cartItem, OrderItemDto.class);
        cartItemRepository.save(cartItem);
        System.out.println("Cart item updated.");
//        System.out.println(orderItemDto);
    }
    orderItemDto.setCartUuid("NA");
    return orderItemDto;
}


    @Override
    @Transactional
    public void removeCartItem(@PathVariable Integer orderItemId) {
        System.out.println("service");
        CartItem cartItem = cartItemRepository.getReferenceById(orderItemId);
//        System.out.println(orderItem);
        if (cartItem.getItemId() == null) {
            throw new NoSuchElementException("This order id does not exist.");
        } else {
            System.out.println("Cart item removed.");
            cartItemRepository.deleteByOrderItemId(Math.toIntExact(cartItem.getOrderItemId()));

        }
    }

    private GetOrderItemDto getOrderItemDtoConversion(CartItem cartItem){
        GetOrderItemDto getOrderItemDto = new GetOrderItemDto();

        getOrderItemDto.setUnitPrice(cartItem.getItemId().getUnitPrice());
        getOrderItemDto.setItemName(cartItem.getItemId().getItemName());
        getOrderItemDto.setTotal(cartItem.getTotal());
        getOrderItemDto.setQuantity(cartItem.getQuantity());
//        System.out.println(getOrderItemDto);
        return getOrderItemDto;
    }
}
