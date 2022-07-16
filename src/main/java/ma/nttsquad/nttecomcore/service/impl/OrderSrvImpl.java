package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.OrderMapper;
import ma.nttsquad.nttecomcore.model.repository.OrderRepository;
import ma.nttsquad.nttecomcore.service.OrderSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderSrvImpl implements OrderSrv {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderDto> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(Long order_id) {
        log.trace("{}", order_id);
        return orderRepository.findById(order_id)
                .map(OrderMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no order with the id '%d'".formatted(order_id)));
    }

    @Override
    public void saveOrder(OrderDto orderDto) {
        log.trace("start save order: {}",orderDto);
        orderRepository.save(OrderMapper.INSTANCE.dtoToEntity(orderDto));
        log.trace("end save order");
    }

    @Override
    public void updateOrder(Long order_id, OrderDto orderDto) {
        log.trace("start update order: {} {}",order_id, orderDto);
        OrderDto order = getOrderById(order_id);
        orderDto.setId(order.getId());
        orderRepository.save(OrderMapper.INSTANCE.dtoToEntity(orderDto));
        log.trace("end update order: {}",order_id);
    }

    @Override
    public void deleteOrder(Long order_id) {
        log.trace("start delete order: {}",order_id);
        orderRepository.deleteById(order_id);
        log.trace("end delete order: {}",order_id);
    }

}
