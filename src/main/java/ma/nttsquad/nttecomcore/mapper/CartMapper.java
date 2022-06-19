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
    default void addBackReference(@MappingTarget CartDto.CartDtoBuilder cartDtoBuilder){
        for(CartItemDto cartItemDto: cartDtoBuilder.build().getCartItems()){
            cartItemDto.setCart(cartDtoBuilder.build());
        }
    }

    @AfterMapping
    default void addBackReference(@MappingTarget Cart.CartBuilder cartBuilder){
        for(CartItem cartItem: cartBuilder.build().getCartItems()){
            cartItem.setCart(cartBuilder.build());
        }
    }
}
