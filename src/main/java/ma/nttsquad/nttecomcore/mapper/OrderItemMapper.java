package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.OrderItemDto;
import ma.nttsquad.nttecomcore.model.OrderItem;

public interface OrderItemMapper {

    OrderItemDto entityToDto (OrderItem orderItem);

    OrderItem dtoToEntity (OrderItemDto orderItemDto);
}
