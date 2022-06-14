package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CategoryDto;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductSrvTest {


    @Mock
    ProductSrv productSrv;

    @Test
    void getAllProducts() {
        List<ProductDto> mockProductDtoList = new ArrayList<>();
        CategoryDto categoryDto = new CategoryDto(3L,"Children");
        mockProductDtoList.add(new ProductDto(1L,null,"desc",15.22,null,categoryDto,1,LocalDateTime.now(),null,null));

        when(productSrv.getAllProducts()).thenReturn(mockProductDtoList);

        List<ProductDto> productDtoList = productSrv.getAllProducts();
        Long id = 1L;

        assertThat(productDtoList).isNotNull().hasSize(1);
        assertThat(productDtoList.get(0).getId()).isEqualTo(id);
    }

    @Test
    void getProductById() {
    }

    @Test
    void getProductByCategoryId() {
    }

    @Test
    void saveProduct() {
    }
}