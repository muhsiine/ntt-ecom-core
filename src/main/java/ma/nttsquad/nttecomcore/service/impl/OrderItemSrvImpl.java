package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CountryDto;
import ma.nttsquad.nttecomcore.dto.OrderItemDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.CountryMapper;
import ma.nttsquad.nttecomcore.mapper.OrderItemMapper;
import ma.nttsquad.nttecomcore.model.repository.CountryRepository;
import ma.nttsquad.nttecomcore.model.repository.OrderItemRepository;
import ma.nttsquad.nttecomcore.service.CountrySrv;
import ma.nttsquad.nttecomcore.service.OrderItemSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderItemSrvImpl implements OrderItemSrv {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        return orderItemRepository.findAll()
                .stream()
                .map(OrderItemMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto getOrderItemById(Long orderItem_id) {
        log.trace("{}", orderItem_id);
        return orderItemRepository.findById(orderItem_id)
                .map(OrderItemMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no order item with the id '%d'".formatted(orderItem_id)));
    }

    @Override
    public void saveOrderItem(OrderItemDto orderItemDto) {
        log.trace("start save order item: {}",orderItemDto);
        orderItemRepository.save(OrderItemMapper.INSTANCE.dtoToEntity(orderItemDto));
        log.trace("end save order item");
    }

    @Override
    public void updateOrderItem(Long orderItem_id, OrderItemDto orderItemDto) {
        log.trace("start update order item: {} {}",orderItem_id, orderItemDto);
        OrderItemDto orderItem = getOrderItemById(orderItem_id);
        orderItemDto.setId(orderItem.getId());
        orderItemRepository.save(OrderItemMapper.INSTANCE.dtoToEntity(orderItemDto));
        log.trace("end update order item: {}",orderItem_id);
    }

    @Override
    public void deleteOrderItem(Long orderItem_id) {
        log.trace("start delete order item: {}",orderItem_id);
        orderItemRepository.deleteById(orderItem_id);
        log.trace("end delete order item: {}",orderItem_id);
    }
}
