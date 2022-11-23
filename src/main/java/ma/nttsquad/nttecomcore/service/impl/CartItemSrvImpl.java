package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.AddressDto;
import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.AddressMapper;
import ma.nttsquad.nttecomcore.mapper.CartItemMapper;
import ma.nttsquad.nttecomcore.mapper.CartMapper;
import ma.nttsquad.nttecomcore.model.repository.CartItemRepository;
import ma.nttsquad.nttecomcore.model.repository.CartRepository;
import ma.nttsquad.nttecomcore.service.CartItemSrv;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartItemSrvImpl implements CartItemSrv {

    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItemDto> getAllCartItems() {
        List<CartItemDto> allCartItemDTO = cartItemRepository.findAll()
                .stream()
                .map(CartItemMapper.INSTANCE::entityToDto)
                .toList();
        if(allCartItemDTO == null || allCartItemDTO.isEmpty()){
            throw new NttNotFoundException("There's no cart items in database");
        }
        return allCartItemDTO;
    }

    @Override
    public CartItemDto getCartItemById(Long cartItem_id) {
        log.trace("{}", cartItem_id);
        return cartItemRepository.findById(cartItem_id)
                .map(CartItemMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no cart item with the id '%d'".formatted(cartItem_id)));
    }

    @Override
    public CartItemDto saveCartItem(CartItemDto cartItemDto) {
        log.trace("save cart item: {}",cartItemDto);
        return CartItemMapper.INSTANCE.entityToDto(cartItemRepository.save(CartItemMapper.INSTANCE.dtoToEntity(cartItemDto)));
    }

    @Override
    public CartItemDto updateCartItem(Long cartItem_id, CartItemDto cartItemDto) {
        log.trace("update cart item: {} {}",cartItem_id, cartItemDto);
        CartItemDto cartItem = getCartItemById(cartItem_id);
        cartItemDto.setId(cartItem.getId());
        return CartItemMapper.INSTANCE.entityToDto(cartItemRepository.save(CartItemMapper.INSTANCE.dtoToEntity(cartItemDto)));
    }

    @Override
    public void deleteCartItem(Long cartItem_id) {
        log.trace("delete cart item: {}",cartItem_id);
        cartItemRepository.deleteById(cartItem_id);
    }
}
