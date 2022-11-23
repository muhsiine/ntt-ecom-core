package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.AddressDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;

import java.util.List;

public interface CartItemSrv {

    List<CartItemDto> getAllCartItems();

    CartItemDto getCartItemById(Long cartItem_id);

    CartItemDto saveCartItem(CartItemDto cartItemDto);

    CartItemDto updateCartItem(Long cartItem_id, CartItemDto cartItemDto);

    void deleteCartItem(Long cartItem_id);
}
