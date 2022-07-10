package ma.nttsquad.nttecomcore.controller;

import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.mapper.CategoryByLangMapper;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.model.CategoryByLang;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
@WebMvcTest(CategoryCtrl.class)
class CategoryCtrlTest {

    @MockBean
    CategoriesSrv categorySrv;
    @Autowired
    MockMvc mockMvc;
    @Test
    void getAllCategoriesByLang() throws Exception {
        Category mockCategorySp = new Category(1L,"sp",null);
        Category mockCategoryBu = new Category(1L,"bu",null);
        CategoryByLang mockCategoryByLang = new CategoryByLang(1L,"Sport", LangCons.EN,mockCategorySp);
        CategoryByLang mockCategoryByLang2 = new CategoryByLang(2L,"Business", LangCons.EN,mockCategoryBu);
        CategoryByLangDto mockCategoryByLangDto = CategoryByLangMapper.INSTANCE.entityToDto(mockCategoryByLang);

        List<CategoryByLangDto> mockCategoryByLangDtoList = new ArrayList<>();
        mockCategoryByLangDtoList.add(mockCategoryByLangDto);

        mockCategoryByLangDto = CategoryByLangMapper.INSTANCE.entityToDto(mockCategoryByLang2);
        mockCategoryByLangDtoList.add(mockCategoryByLangDto);


        when(categorySrv.getAllCategoriesByLang(LangCons.EN)).thenReturn(mockCategoryByLangDtoList);
        mockMvc.perform(get("/categories/all").param("lang",LangCons.EN.toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].description",Matchers.is("Sport")))
                .andExpect(jsonPath("$[1].description",Matchers.is("Business")))
                .andExpect(jsonPath("$[1].langCode",Matchers.is(LangCons.EN.toString())))
                .andReturn();
    }
}