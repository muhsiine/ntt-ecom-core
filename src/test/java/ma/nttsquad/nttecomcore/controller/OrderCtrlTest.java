package ma.nttsquad.nttecomcore.controller;

import ma.nttsquad.nttecomcore.dto.OrderDto;
import ma.nttsquad.nttecomcore.mapper.OrderMapper;
import ma.nttsquad.nttecomcore.model.*;
import ma.nttsquad.nttecomcore.service.OrderSrv;
import ma.nttsquad.nttecomcore.service.impl.OrderSrvImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderCtrl.class)
class OrderCtrlTest {

    @MockBean
    OrderSrv orderSrv;

    @Autowired
    MockMvc mockMvc;

    private List<OrderDto> mockOrderDtoList;
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

        mockOrderList= new ArrayList<>();
        orderItemList= new ArrayList<>();
        mockOrderDtoList= new ArrayList<>();

        mockStatus = new Status(1L,"PENDING");
        user = new User(1L,"yassir_123","Yassir","El Reklaoui","yassir9reklaoui@gmail.com","+212707244096","24/04/1996",null,null);
        category = new Category(3L,"Children", "icon");
        productMock = new Product(1L, null, "desc", 15.22, null, category, 1, LocalDateTime.now(), null, null);

        orderItem = new OrderItem(1L,null,productMock,32);
        orderItemList.add(orderItem);

        mockOrder =  new Order(1L,null,mockStatus,orderItemList, LocalDateTime.now(),LocalDateTime.now(),user);
        mockOrderDto = OrderMapper.INSTANCE.entityToDto(mockOrder);


        mockOrderList.add(mockOrder);
        mockOrderDtoList.add(mockOrderDto);
    }
    @Test
    void getAllOrders() throws Exception {

        when(orderSrv.getAllOrders()).thenReturn(mockOrderDtoList);

        mockMvc.perform(get("/orders/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].status.statusDesc", Matchers.is("PENDING")))
                .andExpect(jsonPath("$[0].user.firstName",Matchers.is("Yassir")))
                .andReturn();

    }

    @Test
    void getOrdersByUserId() throws Exception {
        Long userId=1L;
        when(orderSrv.getOrdersByUserId(userId)).thenReturn(mockOrderDtoList);

        mockMvc.perform(get("/orders/{id}",userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].user.username",Matchers.is("yassir_123")))
                .andReturn();
    }
}