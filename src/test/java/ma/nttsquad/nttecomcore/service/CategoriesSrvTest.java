package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.model.CategoryByLang;
import ma.nttsquad.nttecomcore.model.repository.CategoryByLangRepository;
import ma.nttsquad.nttecomcore.service.impl.CategoriesSrvImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriesSrvTest {

    @Mock
    CategoryByLangRepository categoryByLangRepository;

    CategoriesSrv categoriesSrv;

    @Test
    void getAllCategoriesByLang() {
        categoriesSrv = new CategoriesSrvImpl(categoryByLangRepository);

        Category mockCategory = new Category(1L,"sp",null);
        CategoryByLang mockCategoryByLang = new CategoryByLang(1L,"Sport",LangCons.EN,mockCategory);

        List<CategoryByLang> mockCategoryByLangList = new ArrayList<>();

        mockCategoryByLangList.add(mockCategoryByLang);


        when(categoryByLangRepository.findByLangCode(LangCons.EN)).thenReturn(mockCategoryByLangList);

        List<CategoryByLangDto> categoryByLangDTOList = categoriesSrv.getAllCategoriesByLang(LangCons.EN);

        assertThat(categoryByLangDTOList)
                .isNotNull()
                .hasSize(1)
                .extracting("categoryCode")
                .contains(mockCategory.getCategoryCode());
    }
}