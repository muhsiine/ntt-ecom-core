package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.dto.StatusDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderSrvTest {


    @Mock
    OrderSrv orderSrv;

    @Test
    void getAllOrders() {

        List<OrderDto> mockOrderDtoList = new ArrayList<>();
        mockOrderDtoList.add(new OrderDto(1L,null,new StatusDto(1L,"PENDING"),null, LocalDateTime.now(),LocalDateTime.now(),null));
        mockOrderDtoList.add(new OrderDto(2L,null,new StatusDto(2L,"PAID"),null, LocalDateTime.now(),LocalDateTime.now(),null));

        when(orderSrv.getAllOrders()).thenReturn(mockOrderDtoList);

        List<OrderDto> orderDtoList = orderSrv.getAllOrders();
        Long id = 2L;

        assertThat(orderDtoList).isNotNull().hasSize(2);
        assertThat(orderDtoList.get(1).getId()).isEqualTo(id);
    }

    @Test
    void getOrdersByUserId() {
    }
}