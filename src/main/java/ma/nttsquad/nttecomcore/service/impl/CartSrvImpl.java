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
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartSrvImpl implements CartSrv {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

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
        return cartRepository.findCartByUserId(user_id)
                .map(CartMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no cart belonging the user with the id '%d'".formatted(user_id)));
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
    public void saveCart(CartDto cartDto) {
        log.trace("{}", cartDto);
        cartRepository.save(CartMapper.INSTANCE.dtoToEntity(cartDto));
    }

    @Override
    public void addItemsToCart(List<CartItemDto> cartItems, Long cart_id) {
        log.trace("{}:{}", cartItems, cart_id);
        CartDto cartDto = getCartById(cart_id);
        cartDto.getCartItems()
                .addAll(cartItems);
        cartRepository.save(CartMapper.INSTANCE.dtoToEntity(cartDto));
    }

    @Override
    public void removeItemsFromCart(Long cartId, List<Long> cartItemsId) {
        cartItemRepository.deleteAllById(cartItemsId);
    }
}
