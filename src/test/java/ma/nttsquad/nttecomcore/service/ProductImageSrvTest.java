package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.mapper.ProductImageMapper;
import ma.nttsquad.nttecomcore.model.ProductImage;
import ma.nttsquad.nttecomcore.model.repository.ProductImageRepository;
import ma.nttsquad.nttecomcore.service.impl.ProductImageSrvImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductImageSrvTest {

    //Mocks (dependencies)
    @Mock
    private ProductImageRepository productImageRepository;

    //Class under Test
    private  ProductImageSrv productImageSrv;

    ProductImage productImageMock;

    ProductImageDto productImageDtoMock;

    List<ProductImage> productImageListMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productImageSrv = new ProductImageSrvImpl(productImageRepository);

        productImageMock = new ProductImage(1L, "image url", null);
        productImageDtoMock = ProductImageMapper.INSTANCE.entityToDto(productImageMock);

        productImageListMock = new ArrayList<>();
        productImageListMock.add(productImageMock);
    }

    @Test
    void getImageByProduct() {
        when(productImageRepository.findByProductId(anyLong())).thenReturn(productImageListMock);

        List<ProductImageDto> dtos = productImageSrv.getImageByProduct(1L);

        assertThat(dtos).isNotNull()
                .hasSize(1)
                .extracting("id", "imageUrl")
                .contains(tuple(productImageDtoMock.getId(), productImageDtoMock.getImageUrl()));
    }
}