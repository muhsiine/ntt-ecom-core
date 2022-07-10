package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.OrderItemDto;
import ma.nttsquad.nttecomcore.model.OrderItem;
import org.mapstruct.factory.Mappers;

public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    OrderItemDto entityToDto (OrderItem orderItem);

    OrderItem dtoToEntity (OrderItemDto orderItemDto);
}
