package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.OrderDto;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderSrv {

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrdersByUserId(Long userId);

    OrderDto getOrderById(Long order_id);

    OrderDto saveOrder(OrderDto orderDto);

    OrderDto updateOrder(Long order_id, OrderDto orderDto);

    void deleteOrder(Long order_id);
}
