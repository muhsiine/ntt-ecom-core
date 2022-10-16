package ma.nttsquad.nttecomcore.controller;

import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.mapper.ProductImageMapper;
import ma.nttsquad.nttecomcore.model.ProductImage;
import ma.nttsquad.nttecomcore.service.ProductImageSrv;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductImageCtrl.class)
class ProductImageCtrlTest {


    @MockBean
    ProductImageSrv productImageSrv;
    @Autowired
    MockMvc mockMvc;
    ProductImage productImageMock;

    ProductImageDto productImageDtoMock;

    List<ProductImageDto> productImageDtoListMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        productImageMock = new ProductImage(1L, "image url", null);
        productImageDtoMock = ProductImageMapper.INSTANCE.entityToDto(productImageMock);

        productImageDtoListMock = new ArrayList<>();
        productImageDtoListMock.add(productImageDtoMock);
    }

    @Test
    void getImageByProduct() throws Exception {
        Long productId=1L;
        when(productImageSrv.getImageByProduct(productId)).thenReturn(productImageDtoListMock);

        mockMvc.perform(get("/images/{id}",productId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].imageUrl",Matchers.is("image url")))
                .andReturn();
    }
}