package ma.nttsquad.nttecomcore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.mapper.CartItemMapper;
import ma.nttsquad.nttecomcore.mapper.CartMapper;
import ma.nttsquad.nttecomcore.mapper.ProductMapper;
import ma.nttsquad.nttecomcore.model.*;
import ma.nttsquad.nttecomcore.service.CartSrv;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CartCtrl.class)
class CartCtrlTest {

    @MockBean
    CartSrv cartSrv;
    @Autowired
    MockMvc mockMvc;
    private  List<Cart> mockCartList;
    private  List<CartDto> mockCartDtoList;
    private List<CartItem> mockCartItemList;
    private List<CartItemDto> mockCartItemDtoList;
    private CartItem mockCartItem;
    private CartItemDto mockCartItemDto;
    private CartDto mockCartDto;
    private Cart mockCart;
    private Product productMock;
    private Category category;
    private User user;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        mockCartList = new ArrayList<>();
        mockCartItemList=new ArrayList<>();
        mockCartItemDtoList=new ArrayList<>();
        mockCartDtoList=new ArrayList<>();

        category = new Category(3L,"Children", "icon");
        productMock = new Product(1L, null, "desc", 15.22, null, category, 1, null, null, null,null);

        mockCartItem = new CartItem(1L,null,productMock,5);
        mockCartItemDto = CartItemMapper.INSTANCE.entityToDto(mockCartItem);

        mockCartItemList.add(mockCartItem);

        user = new User(1L,"yassir_123","Yassir","El Reklaoui","yassir9reklaoui@gmail.com","yaseer123","+212707244096","24/04/1996",null,null,null);

        mockCart = new Cart(1L,mockCartItemList,user);
        mockCartDto = CartMapper.INSTANCE.entityToDto(mockCart);

        mockCartList.add(mockCart);
        mockCartDtoList.add(mockCartDto);
        mockCartItemDtoList.add(mockCartItemDto);
    }
    @Test
    void getAll() throws Exception {
        when(cartSrv.getAllCarts()).thenReturn(mockCartDtoList);

        mockMvc.perform(get("/carts/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].user.firstName",Matchers.is("Yassir")))
                .andReturn();
    }

    @Test
    void getCartByUserId() throws Exception {
        Long userId=1L;
        when(cartSrv.getCartByUserId(userId)).thenReturn(mockCartDto);

        mockMvc.perform(get("/carts/user/{userId}",userId)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.user.username",Matchers.is("yassir_123")))
                .andReturn();
    }

    @Test
    void getCartById() throws Exception {
        Long userId=1L;
        when(cartSrv.getCartById(userId)).thenReturn(mockCartDto);

        mockMvc.perform(get("/carts/{id}",userId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.user.username",Matchers.is("yassir_123")))
                .andReturn();
    }


    @Test
    void getCartItemsByCartId() throws Exception {
        Long cartId=1L;
        when(cartSrv.getCartItemsByCartId(cartId)).thenReturn(mockCartItemDtoList);

        mockMvc.perform(get("/carts/{cartId}/cartItems",cartId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].quantity").value(5))
                .andReturn();
    }

    @Test
    void saveCart() throws Exception {
        when(cartSrv.saveCart(any())).thenReturn(mockCartDto);

        mockMvc.perform(post("/carts/save")
                        .content(asJsonString(mockCartDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.user.username",Matchers.is("yassir_123")))
                .andReturn();
    }

    @Test
    void addItemsToCart() throws Exception {
        Long cartId=1L;
        when(cartSrv.addItemsToCart(mockCartItemDtoList,cartId)).thenReturn(mockCartDto);

        mockMvc.perform(post("/carts/{cartId}/add/cartItems/",cartId)
                        .content(asJsonString(mockCartItemDtoList))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.cartItems",Matchers.hasSize(1)))
                .andExpect(jsonPath("$.cartItems[0].quantity").value(5))
                .andExpect(jsonPath("$.user.firstName",Matchers.is("Yassir")))
                .andReturn();
    }

    @Test
    void removeItemsFromCart() throws Exception {
        Long cartId=1L;
        List<Long> cartItemsId = Arrays.asList(1L,2L);

        mockMvc.perform(post("/carts/{cartId}/remove/cartItems",cartId)
                        .content(asJsonString(cartItemsId))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}