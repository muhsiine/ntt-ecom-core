package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CountryDto;
import ma.nttsquad.nttecomcore.dto.OrderItemDto;

import java.util.List;

public interface OrderItemSrv {

    List<OrderItemDto> getAllOrdersItems();

    OrderItemDto getOrderItemById(Long orderItem_id);

    OrderItemDto saveOrderItem(OrderItemDto orderItemDto);

    OrderItemDto updateOrderItem(Long orderItem_id, OrderItemDto orderItemDto);

    void deleteOrderItem(Long orderItem_id);
}
