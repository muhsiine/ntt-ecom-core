package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.OrderItemDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.OrderItemMapper;
import ma.nttsquad.nttecomcore.model.repository.OrderItemRepository;
import ma.nttsquad.nttecomcore.service.OrderItemSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderItemSrvImpl implements OrderItemSrv {

    final OrderItemRepository orderItemRepository;

    @Override
    public List<OrderItemDto> getAllOrdersItems() {
        List<OrderItemDto> AllOrderItemDTO = orderItemRepository.findAll()
                .stream()
                .map(OrderItemMapper.INSTANCE::entityToDto)
                .toList();
        if(AllOrderItemDTO == null || AllOrderItemDTO.isEmpty()){
            throw new NttNotFoundException("There's no orders items in database");
        }
        return AllOrderItemDTO;
    }

    @Override
    public OrderItemDto getOrderItemById(Long orderItem_id) {
        log.trace("{}", orderItem_id);
        return orderItemRepository.findById(orderItem_id)
                .map(OrderItemMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no order item with the id '%d'".formatted(orderItem_id)));
    }

    @Override
    public OrderItemDto saveOrderItem(OrderItemDto orderItemDto) {
        log.trace("save order item: {}",orderItemDto);
        return OrderItemMapper.INSTANCE.entityToDto(orderItemRepository.save(OrderItemMapper.INSTANCE.dtoToEntity(orderItemDto)));
    }

    @Override
    public OrderItemDto updateOrderItem(Long orderItem_id, OrderItemDto orderItemDto) {
        log.trace("update order item: {} {}",orderItem_id, orderItemDto);
        OrderItemDto orderItem = getOrderItemById(orderItem_id);
        orderItemDto.setId(orderItem.getId());
        return OrderItemMapper.INSTANCE.entityToDto(orderItemRepository.save(OrderItemMapper.INSTANCE.dtoToEntity(orderItemDto)));
    }

    @Override
    public void deleteOrderItem(Long orderItem_id) {
        log.trace("delete order item: {}",orderItem_id);
        orderItemRepository.deleteById(orderItem_id);
    }
}
