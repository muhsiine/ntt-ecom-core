package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.mapper.OrderMapper;
import ma.nttsquad.nttecomcore.model.repository.OrderRepository;
import ma.nttsquad.nttecomcore.service.OrderSrv;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSrvImpl implements OrderSrv {

    final OrderRepository orderRepository;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper.INSTANCE::entityToDto)
                .toList();
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper.INSTANCE::entityToDto)
                .toList();
    }
}
