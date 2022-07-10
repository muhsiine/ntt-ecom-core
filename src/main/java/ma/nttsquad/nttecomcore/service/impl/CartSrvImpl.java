package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.CartMapper;
import ma.nttsquad.nttecomcore.model.repository.CartItemRepository;
import ma.nttsquad.nttecomcore.model.repository.CartRepository;
import ma.nttsquad.nttecomcore.service.CartSrv;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartSrvImpl implements CartSrv {

    final CartRepository cartRepository;
    final CartItemRepository cartItemRepository;

    @Override
    public List<CartDto> getAllCarts() {
        return cartRepository.findAll()
                .stream()
                .map(CartMapper.INSTANCE::entityToDto)
                .toList();
    }

    @Override
    public CartDto getCartByUserId(Long userId) {
        log.trace("{}", userId);
        return cartRepository.findCartByUserId(userId)
                .map(CartMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no cart belonging the user with the id '%d'".formatted(userId)));
    }

    @Override
    public CartDto getCartById(Long id) {
        log.trace("{}", id);

        return cartRepository.findById(id)
                .map(CartMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no cart with the id '%d'".formatted(id)));
    }

    @Override
    public List<CartItemDto> getCartItemsByCartId(Long cartId) {
        log.trace("{}", cartId);
        CartDto cartDto = getCartById(cartId);
        return cartDto.getCartItems();
    }

    @Override
    public CartDto saveCart(CartDto cartDto) {
        log.trace("{}", cartDto);
        return CartMapper.INSTANCE.entityToDto(cartRepository.save(CartMapper.INSTANCE.dtoToEntity(cartDto)));
    }

    @Override
    public CartDto addItemsToCart(List<CartItemDto> cartItems, Long cartId) {
        log.trace("{}:{}", cartItems, cartId);
        CartDto cartDto = getCartById(cartId);
        cartDto.getCartItems().addAll(cartItems);
        return CartMapper.INSTANCE.entityToDto(cartRepository.save(CartMapper.INSTANCE.dtoToEntity(cartDto)));
    }

    @Override
    public void removeItemsFromCart(Long cartId, List<Long> cartItemsId) {
        cartItemRepository.deleteAllById(cartItemsId);
    }
}
