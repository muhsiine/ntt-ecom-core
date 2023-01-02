package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
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
}
