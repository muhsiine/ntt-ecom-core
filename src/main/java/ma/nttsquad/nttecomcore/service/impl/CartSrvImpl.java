package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.exception.NttBadRequestException;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.CartItemMapper;
import ma.nttsquad.nttecomcore.mapper.CartMapper;
import ma.nttsquad.nttecomcore.model.repository.CartItemRepository;
import ma.nttsquad.nttecomcore.model.repository.CartRepository;
import ma.nttsquad.nttecomcore.model.repository.ProductRepository;
import ma.nttsquad.nttecomcore.service.CartSrv;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CartSrvImpl implements CartSrv {

    final CartRepository cartRepository;
    final CartItemRepository cartItemRepository;

    final ProductRepository productRepository;

    @Override
    public List<CartDto> getAllCarts() {
        List<CartDto> allCartDTO = cartRepository.findAll()
                .stream()
                .map(CartMapper.INSTANCE::entityToDto)
                .toList();
        if(allCartDTO.isEmpty()){
            throw new NttNotFoundException("There's no cart in data base");
        }
        return allCartDTO;
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
        if(cartDto.getCartItems() == null || cartDto.getCartItems().isEmpty()){
            throw new NttNotFoundException("The cart doesn't contain any cart items");
        }
        return cartDto.getCartItems();
    }

    @Override
    public CartDto saveCart(CartDto cartDto) {
        log.trace("{}", cartDto);
        try{
            return CartMapper.INSTANCE.entityToDto(cartRepository.save(CartMapper.INSTANCE.dtoToEntity(cartDto)));
        }catch(IllegalArgumentException ex){
            throw new NttBadRequestException(ex.getLocalizedMessage());
        }
    }

    @Override
    public CartDto addItemsToCart(List<CartItemDto> cartItems, Long cartId) {
        log.trace("{}:{}", cartItems, cartId);
        CartDto cartDto = getCartById(cartId);
        for (CartItemDto cartItem : cartItems) {
            if(!productRepository.findById(cartItem.getProduct().getId()).isPresent()){
                throw new NttNotFoundException("There's no Product with the id '%d'".formatted(cartItem.getProduct().getId()));
            }
        }
        cartDto.getCartItems().addAll(cartItems);
        return CartMapper.INSTANCE.entityToDto(cartRepository.save(CartMapper.INSTANCE.dtoToEntity(cartDto)));
    }

    @Override
    public CartDto removeItemsFromCart(Long cartId, List<Long> cartItemsId) {
        boolean hasCartItems = false;
        CartDto cartDto = getCartById(cartId);
        CartDto newCartDto ;
        if(!cartDto.getCartItems().isEmpty()) {
            for (Long cartItemId : cartItemsId) {
                CartItemDto cartItemDto = cartDto.getCartItems().stream()
                        .filter(cartItem -> cartItem.getId().equals(getCartItemById(cartItemId).getId()))
                        .findAny()
                        .orElse(null);
                if (cartItemDto != null) {
                    cartDto.getCartItems().remove(cartItemDto);
                    cartItemRepository.delete(CartItemMapper.INSTANCE.dtoToEntity(cartItemDto));
                    hasCartItems = true;
                }
            }
        }
        else{
            throw new NttNotFoundException("The cart doesn't contain any cart items");
        }
        if(hasCartItems)
            newCartDto=cartDto;
        else
            throw new NttNotFoundException("The cart doesn't contain any selected cart items");

        return newCartDto;
    }


    private CartItemDto getCartItemById(Long id) {
        log.trace("{}", id);

        return cartItemRepository.findById(id)
                .map(CartItemMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no cart Item with the id '%d'".formatted(id)));
    }
}
