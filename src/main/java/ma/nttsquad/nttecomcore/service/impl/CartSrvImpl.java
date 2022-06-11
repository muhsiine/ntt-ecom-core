package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.mapper.CartMapper;
import ma.nttsquad.nttecomcore.model.CartItem;
import ma.nttsquad.nttecomcore.model.repository.CartRepository;
import ma.nttsquad.nttecomcore.service.CartSrv;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartSrvImpl implements CartSrv {

    final CartRepository cartRepository;

    @Override
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(CartMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CartDto getCartByUser(Long user_id) {
        log.trace("{}", user_id);
        return CartMapper.INSTANCE.entityToDto(cartRepository.getCartByUser(user_id));
    }

    @Override
    public CartDto getCartById(Long id) {
        log.trace("{}", id);
        return CartMapper.INSTANCE.entityToDto(cartRepository.getById(id));
    }

    @Override
    public List<CartItemDto> getCartItemsByCartId(Long cart_id) {
        log.trace("{}", cart_id);
        CartDto cart = getCartById(cart_id);
        List<CartItemDto> listCartItems = new ArrayList<>();
        if(cart != null){
            listCartItems = cart.getCartItems()
                    .stream()
                    .collect(Collectors.toList());
        }
        return listCartItems;
    }

    @Override
    public void saveCart(CartDto cartDto) {
        log.trace("{}", cartDto);
        cartRepository.save(CartMapper.INSTANCE.dtoToEntity(cartDto));
    }

    @Override
    public void addItemsToCart(List<CartItemDto> cartItems, Long cart_id) {
        log.trace("{}:{}", cartItems, cart_id);
        CartDto cart = getCartById(cart_id);
        cart.setCartItems(cartItems);
    }

    @Override
    public void removeItemsFromCard(Long cart_id, Long cartItem_id) {
        log.trace("{}:{}", cart_id, cartItem_id);
        CartDto cart = getCartById(cart_id);
        if(!CollectionUtils.isEmpty(cart.getCartItems())){
            cart.getCartItems().removeIf(cartItem -> cartItem.getId().equals(cartItem_id));
        }
    }
}
