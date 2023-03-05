package com.adamstraub.tonsoftacos.tonsoftacos.controller.orderItemControllers;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.MenuItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.CartItemRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.GetOrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.OrderItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.CartItem;
import com.adamstraub.tonsoftacos.tonsoftacos.service.orderItemServices.CartItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.InvalidPropertiesFormatException;
import java.util.List;


@RestController
public class CartItemController implements CartItemControllerInterface {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private MenuItemRepository menuItemRepository;

    @Transactional
    @Override
    public String addToCart(@RequestBody OrderItemDto orderItemDto) throws InvalidPropertiesFormatException {
//        System.out.println(orderItemDto);
        System.out.println("controller");
        String verification = "{" + "\"response\" : \"Item added to cart.\"" + "}";
        CartItem request = modelMapper.map(orderItemDto, CartItem.class);
        cartItemService.addToCart(request);
        System.out.println(verification);
        return verification;
    }

    @Override
    public List<GetOrderItemDto> findByCartUuid(String cartUuid) {
        System.out.println("controller");
        return cartItemService.findByCartUuid(cartUuid);
    }


    @Override
    public OrderItemDto updateCart(Integer orderItemId, Integer newQuantity) {
        System.out.println("controller");
        return cartItemService.updateCart(orderItemId, newQuantity);
    }

    @Override
    public void removeCartItem(Integer orderItemId) {
        System.out.println("controller");
    cartItemService.removeCartItem(orderItemId);
    }

}