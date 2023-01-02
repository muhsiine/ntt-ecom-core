package ma.nttsquad.nttecomcore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.mapper.ProductMapper;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.model.Product;
import ma.nttsquad.nttecomcore.service.ProductSrv;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductCtrl.class)
class ProductCtrlTest {

    @MockBean
    ProductSrv productSrv;

    @Autowired
    MockMvc mockMvc;

    private Product productMock;
    private ProductDto productDtoMock;

    private Category category;
    private List<ProductDto> mockProducListDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        category = new Category(3L,"Children", "icon");
        productMock = new Product(1L, null, "desc", 15.22, null, category, 1, null, null, null,null);
        productDtoMock = ProductMapper.INSTANCE.entityToDto(productMock);

        mockProducListDto = new ArrayList<>();
        mockProducListDto.add(productDtoMock);
    }

    @Test
    void getAllProducts() throws Exception {
        when(productSrv.getAllProducts()).thenReturn(mockProducListDto);

        mockMvc.perform(get("/products/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].description",Matchers.is("desc")))
                .andExpect(jsonPath("$[0].category.icon",Matchers.is("icon")))
                .andReturn();
    }

    @Test
    void getProductById() throws Exception {

        Long productId=1L;
        when(productSrv.getProductById(productId)).thenReturn(productDtoMock);

        mockMvc.perform(get("/products/{id}",productId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.description").value("desc"))
                .andExpect(jsonPath("$.price").value(15.22))
                .andExpect(jsonPath("$.category.icon",Matchers.is("icon")))
                .andReturn();

    }

    @Test
    void getProductByCategoryId() throws Exception {
        Long categoryId=1L;
        when(productSrv.getProductByCategoryId(categoryId)).thenReturn(mockProducListDto);

        mockMvc.perform(get("/products/category/{id}",categoryId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].description",Matchers.is("desc")))
                .andExpect(jsonPath("$[0].price",Matchers.is(15.22)))
                .andExpect(jsonPath("$[0].category.icon",Matchers.is("icon")))
                .andReturn();
    }

    @Test
    void saveProduct() throws Exception {
        when(productSrv.saveProduct(any())).thenReturn(productDtoMock);

        mockMvc.perform(post("/products/save")
                        .content(asJsonString(productDtoMock))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.description",Matchers.is("desc")))
                .andExpect(jsonPath("$.category.icon",Matchers.is("icon")))
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