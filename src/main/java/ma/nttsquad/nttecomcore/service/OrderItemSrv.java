package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CountryDto;
import ma.nttsquad.nttecomcore.dto.OrderItemDto;

import java.util.List;

public interface OrderItemSrv {

    List<OrderItemDto> getAllOrderItems();

    OrderItemDto getOrderItemById(Long orderItem_id);

    void saveOrderItem(OrderItemDto orderItemDto);

    void updateOrderItem(Long orderItem_id, OrderItemDto orderItemDto);

    void deleteOrderItem(Long orderItem_id);
}
