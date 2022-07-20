package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.OrderDto;

import java.util.List;

public interface OrderSrv {

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrdersByUserId(Long userId);
}
