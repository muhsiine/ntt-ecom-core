package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.ProductMapper;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.model.Product;
import ma.nttsquad.nttecomcore.model.repository.ProductRepository;
import ma.nttsquad.nttecomcore.service.impl.ProductSrvImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductSrvTest {

    //Mocks (dependencies)
    @Mock
    private ProductRepository productRepository;

    //Class under Test
    private  ProductSrv productSrv;



    private Product productMock;
    private ProductDto productDtoMock;

    private Category category;
    private List<Product> mockProducList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productSrv = new ProductSrvImpl(productRepository);

        category = new Category(3L,"Children", "icon");
        productMock = new Product(1L, null, "desc", 15.22, null, category, 1, LocalDateTime.now(), null, null);
        productDtoMock = ProductMapper.INSTANCE.entityToDto(productMock);

        mockProducList = new ArrayList<>();
        mockProducList.add(productMock);
    }

    @Test
    void getAllProducts() {

        long expectedId = 1L;

        when(productRepository.findAll()).thenReturn(mockProducList);

        List<ProductDto> productDtoList = productSrv.getAllProducts();
        assertThat(productDtoList).isNotNull().hasSize(1);
        assertThat(productDtoList.get(0).getId()).isEqualTo(expectedId);
    }

    @Test
    void getProductById() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(productMock));

        ProductDto productDto = productSrv.getProductById(1L);

        assertNotNull(productDto);
        assertThat(productDto).extracting("id", "description", "price")
                        .contains(productDtoMock.getId(), productDtoMock.getDescription(), productDtoMock.getPrice());


    }

    @Test
    void getProductByIdKo() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NttNotFoundException.class, () -> productSrv.getProductById(-1L));
    }

    @Test
    void getProductByCategoryId() {
        long expectedId = 1L;
        String expectedDesc = "desc";
        when(productRepository.findByCategoryId(anyLong())).thenReturn(mockProducList);

        List<ProductDto> productDtoList = productSrv.getProductByCategoryId(category.getId());

        assertThat(productDtoList)
                .isNotNull()
                .hasSize(1)
                .extracting("id", "description")
                .contains(tuple(expectedId, expectedDesc));


    }

    @Test
    void saveProduct() {

        productSrv.saveProduct(productDtoMock);
        verify(productRepository, times(1)).save(any());

    }
}