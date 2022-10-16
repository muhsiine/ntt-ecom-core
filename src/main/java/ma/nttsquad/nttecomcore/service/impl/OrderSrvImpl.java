package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
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
    public List<OrderDto> getAllOrders() {
        List<OrderDto> allOrdersDTO = orderRepository.findAll()
                .stream()
                .map(OrderMapper.INSTANCE::entityToDto)
                .toList();
        if(allOrdersDTO == null || allOrdersDTO.isEmpty()){
            throw new NttNotFoundException("There's no Orders in data base");
        }
        return allOrdersDTO;
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        List<OrderDto> allOrdersDTO = orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper.INSTANCE::entityToDto)
                .toList();
        if(allOrdersDTO == null || allOrdersDTO.isEmpty()){
            throw new NttNotFoundException("There's no Orders belonging the user with the id '%d'".formatted(userId));
        }
        return allOrdersDTO;
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
