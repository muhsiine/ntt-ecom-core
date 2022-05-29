package ma.nttsquad.nttecomcore.service.impl;

import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.mapper.OrderMapper;
import ma.nttsquad.nttecomcore.repository.OrderRepository;
import ma.nttsquad.nttecomcore.service.OrderSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
}
