package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.mapper.OrderMapper;
import ma.nttsquad.nttecomcore.model.*;
import ma.nttsquad.nttecomcore.model.repository.OrderRepository;
import ma.nttsquad.nttecomcore.service.impl.OrderSrvImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderSrvTest {

    @Mock
    private OrderRepository orderRepository;
    private OrderSrv orderSrv;
    private  List<OrderDto> mockOrderDtoList;
    private  List<Order> mockOrderList;
    private Order mockOrder;
    private OrderItem orderItem;
    private List<OrderItem> orderItemList;
    private OrderDto mockOrderDto;
    private Status mockStatus;
    private User user;
    private Product productMock;
    private Category category;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        orderSrv = new OrderSrvImpl(orderRepository);

        mockOrderList= new ArrayList<>();
        orderItemList= new ArrayList<>();

        mockStatus = new Status(1L,"PENDING");
        user = new User(1L,"yassir_123","Yassir","El Reklaoui","yassir9reklaoui@gmail.com","+212707244096","24/04/1996",null,null);
        category = new Category(3L,"Children", "icon");
        productMock = new Product(1L, null, "desc", 15.22, null, category, 1, LocalDateTime.now(), null, null);

        orderItem = new OrderItem(1L,null,productMock,32);
        orderItemList.add(orderItem);

        mockOrder =  new Order(1L,null,mockStatus,orderItemList, LocalDateTime.now(),LocalDateTime.now(),user);
        mockOrderDto = OrderMapper.INSTANCE.entityToDto(mockOrder);


        mockOrderList.add(mockOrder);
    }

    @Test
    void getAllOrders() {

        when(orderRepository.findAll()).thenReturn(mockOrderList);

        List<OrderDto> orderDtoList = orderSrv.getAllOrders();

        assertThat(orderDtoList)
                .isNotNull()
                .hasSize(1)
                .extracting("id","status","user.username")
                .contains(tuple(mockOrderDto.getId(),mockOrderDto.getStatus(),mockOrderDto.getUser().getUsername()));
    }

    @Test
    void getOrdersByUserId() {
        Long userId = 1L;
        when(orderRepository.findByUserId(userId)).thenReturn(mockOrderList);

        List<OrderDto> orderDtoList = orderSrv.getOrdersByUserId(userId);

        assertThat(orderDtoList)
                .isNotNull()
                .extracting("id","status","user.username")
                .contains(tuple(mockOrderDto.getId(),mockOrderDto.getStatus(),mockOrderDto.getUser().getUsername()));

    }
}