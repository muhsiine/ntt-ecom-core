package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.dto.OrderItemDto;
import ma.nttsquad.nttecomcore.model.CartItem;
import ma.nttsquad.nttecomcore.model.OrderItem;
import org.mapstruct.factory.Mappers;

public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    CartItemDto entityToDto (CartItem cartItem);

    CartItem dtoToEntity (CartItemDto cartItemDto);
}
