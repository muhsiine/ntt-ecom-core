package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface CartItemMapper {

    CartItemMapper INSTANCE = Mappers.getMapper(CartItemMapper.class);

    @Mapping(target = "cart", ignore = true)
    CartItemDto entityToDto (CartItem cartItem);
    @Mapping(target = "cart", ignore = true)
    CartItem dtoToEntity (CartItemDto cartItemDto);


}
