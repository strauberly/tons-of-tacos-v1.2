package com.adamstraub.tonsoftacos.tonsoftacos.services.cartItemServices;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.cartItemsDto.GetCartItemDto;
import com.adamstraub.tonsoftacos.tonsoftacos.entities.CartItem;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.cartItemsDto.CartItemDto;


import java.util.InvalidPropertiesFormatException;
import java.util.List;

public interface CartItemServiceInterface {
    @Transactional
    void addToCart(CartItem cartItem) throws InvalidPropertiesFormatException;

    @Transactional(readOnly = true)
    List<GetCartItemDto> findByCartUuid(String cartUuid);

    @Transactional
    CartItemDto updateCart(@PathVariable Integer orderItemId, @RequestBody Integer newQuantity);

    @Transactional
    void removeCartItem(@PathVariable Integer orderItemId);
}
