package ma.nttsquad.nttecomcore.service;


import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;

import java.util.List;

public interface CartSrv {

    List<CartDto> getAllCarts();

    CartDto getCartByUser(Long user_id);

    CartDto getCartById(Long id);

    List<CartItemDto> getCartItemsByCartId(Long cart_id);

    void saveCart(CartDto cartDto);

    void addItemsToCart(List<CartItemDto> cartItems, Long cart_id);

    void removeItemsFromCart(Long cart_id, List<Long> cartItem_id);

}
