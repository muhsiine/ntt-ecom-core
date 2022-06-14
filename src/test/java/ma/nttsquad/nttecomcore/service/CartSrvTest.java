package ma.nttsquad.nttecomcore.service;

import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.dto.CartDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartSrvTest {

    @Mock
    CartSrv cartSrv;

    @Test
    void getAllCarts() {

        List<CartDto> mockCartDtoList = new ArrayList<>();
        mockCartDtoList.add(new CartDto(1L,null,null));

        when(cartSrv.getAllCarts()).thenReturn(mockCartDtoList);

        List<CartDto> cartDtoList = cartSrv.getAllCarts();
        Long id = 1L;

        assertThat(cartDtoList).isNotNull().hasSize(1);
        assertThat(cartDtoList.get(0).getId()).isEqualTo(id);
    }

    @Test
    void getCartByUser() {
    }

    @Test
    void getCartById() {
    }

    @Test
    void getCartItemsByCartId() {
    }

    @Test
    void saveCart() {
    }

    @Test
    void addItemsToCart() {
    }

    @Test
    void removeItemsFromCart() {
    }
}