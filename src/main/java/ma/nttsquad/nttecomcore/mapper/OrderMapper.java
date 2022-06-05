package ma.nttsquad.nttecomcore.mapper;

import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDto entityToDto (Order order);

    Order dtoToEntity (OrderDto orderDto);
}
