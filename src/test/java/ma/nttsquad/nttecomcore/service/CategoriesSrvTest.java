package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriesSrvTest {

    @Mock
    CategoriesSrv categoriesSrv;

    @Test
    void getAllCategories() {

        List<CategoryDto> mockCategoryDtoList = new ArrayList<>();
        CategoryDto categoryDto = new CategoryDto(3L,"Children");
        mockCategoryDtoList.add(new CategoryDto(1L,"Men"));
        mockCategoryDtoList.add(new CategoryDto(2L,"Women"));
        mockCategoryDtoList.add(categoryDto);

        when(categoriesSrv.getAllCategories()).thenReturn(mockCategoryDtoList);

        List<CategoryDto> categoryDtoList = categoriesSrv.getAllCategories();

        assertThat(categoryDtoList).isNotNull().hasSize(3);
        assertThat(categoryDtoList.get(2)).isEqualTo(categoryDto);
    }
}