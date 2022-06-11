package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDto entityToDto (Cart cart);

    Cart dtoToEntity (CartDto cartDto);
}
