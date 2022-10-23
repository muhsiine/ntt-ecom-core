package ma.nttsquad.nttecomcore.service;


import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;

import java.util.List;

public interface CartSrv {

    List<CartDto> getAllCarts();

    CartDto getCartByUserId(Long userId);

    CartDto getCartById(Long id);

    List<CartItemDto> getCartItemsByCartId(Long cartId);

    CartDto saveCart(CartDto cartDto);

    CartDto addItemsToCart(List<CartItemDto> cartItems, Long cartId);

    CartDto removeItemsFromCart(Long cartId, List<Long> cartItemId);

}
