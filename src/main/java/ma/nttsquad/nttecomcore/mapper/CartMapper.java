package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.model.Cart;
import ma.nttsquad.nttecomcore.model.CartItem;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CartItemMapper.class)
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);


    CartDto entityToDto (Cart cart);

    Cart dtoToEntity (CartDto cartDto);

    @AfterMapping
    default void addBackReference(@MappingTarget CartDto cartDto){
        for(CartItemDto cartItemDto: cartDto.getCartItems()){
            cartItemDto.setCart(cartDto);
        }
    }

    @AfterMapping
    default void addBackReference(@MappingTarget Cart cart){
        for(CartItem cartItem: cart.getCartItems()){
            cartItem.setCart(cart);
        }
    }
}
