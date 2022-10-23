package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.mapper.CartMapper;
import ma.nttsquad.nttecomcore.mapper.ProductMapper;
import ma.nttsquad.nttecomcore.model.*;
import ma.nttsquad.nttecomcore.model.repository.CartItemRepository;
import ma.nttsquad.nttecomcore.model.repository.CartRepository;
import ma.nttsquad.nttecomcore.service.impl.CartSrvImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartSrvTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;
    private CartSrv cartSrv;
    private  List<Cart> mockCartList;
    private List<CartItem> mockCartItemList;
    private CartItem mockCartItem;
    private CartDto mockCartDto;
    private Cart mockCart;
    private Product productMock;
    private ProductDto productDtoMock;
    private Category category;

    private User user;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cartSrv = new CartSrvImpl(cartRepository,cartItemRepository);

        mockCartList = new ArrayList<>();
        mockCartItemList=new ArrayList<>();

        category = new Category(3L,"Children", "icon");
        productMock = new Product(1L, null, "desc", 15.22, null, category, 1, LocalDateTime.now(), null, null,null);
        productDtoMock = ProductMapper.INSTANCE.entityToDto(productMock);

        mockCartItem = new CartItem(1L,null,productMock,5);
        mockCartItemList.add(mockCartItem);

        user = new User(1L,"yassir_123","Yassir","El Reklaoui","yassir9reklaoui@gmail.com","yasser123","+212707244096","24/04/1996",null,null,null);

        mockCart = new Cart(1L,mockCartItemList,user);
        mockCartDto = CartMapper.INSTANCE.entityToDto(mockCart);

        mockCartList.add(mockCart);
    }

    @Test
    void getAllCarts() {

        Long expectedId = 1L;

        when(cartRepository.findAll()).thenReturn(mockCartList);

        List<CartDto> cartDtoList = cartSrv.getAllCarts();

        assertThat(cartDtoList).isNotNull().hasSize(1);
        assertThat(cartDtoList.get(0).getId()).isEqualTo(expectedId);
    }

    @Test
    void getCartByUser() {

        when(cartRepository.findCartByUserId(1L)).thenReturn(Optional.of(mockCart));
        CartDto cartDto = cartSrv.getCartByUserId(1L);
        assertThat(cartDto).isNotNull()
                            .extracting("id")
                            .isEqualTo(mockCartDto.getId());
    }

    @Test
    void getCartById() {

        when(cartRepository.findById(1L)).thenReturn(Optional.of(mockCart));
        CartDto cartDto = cartSrv.getCartById(1L);
        assertThat(cartDto).isNotNull()
                            .extracting("id")
                            .isEqualTo(mockCartDto.getId());

    }

    @Test
    void getCartItemsByCartId() {

        when(cartRepository.findById(1L)).thenReturn(Optional.of(mockCart));
        List<CartItemDto> cartItemDtoList = cartSrv.getCartItemsByCartId(1L);
        assertThat(cartItemDtoList).isNotNull()
                                    .hasSize(1)
                                    .extracting("quantity")
                                    .contains(mockCartItem.getQuantity());

    }

    @Test
    void saveCart() {
        when(cartRepository.save(any())).thenReturn(mockCart);
        CartDto cartDto = cartSrv.saveCart(mockCartDto);
        assertThat(cartDto)
                .isNotNull()
                .extracting("id")
                .isEqualTo(mockCartDto.getId());
        verify(cartRepository, times(1)).save(any());
    }

    @Test
    void addItemsToCart() {

        when(cartRepository.findById(1L)).thenReturn(Optional.of(mockCart));
        when(cartRepository.save(any())).thenReturn(mockCart);


        List<CartItemDto> cartItemDtoList = cartSrv.getCartItemsByCartId(1L);
        cartItemDtoList.add(new CartItemDto(2L,null,null,8));

        CartDto cartDto =cartSrv.addItemsToCart(cartItemDtoList,1L);
        assertThat(cartDto)
                .isNotNull()
                .extracting("id")
                .isEqualTo(mockCartDto.getId());

        verify(cartRepository, times(1)).save(any());

    }
    @Test
    void removeItemsFromCart() {
        cartSrv.removeItemsFromCart(1L, Arrays.asList(1L,2L));
        verify(cartItemRepository, times(1)).deleteAllById(any());
    }

}