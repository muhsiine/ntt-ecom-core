package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.AddressDto;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.dto.CategoryDto;

import java.util.List;

public interface CategoriesSrv {

    List<CategoryByLangDto> getAllCategoriesByLang(LangCons langCode);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(Long category_id);

    void saveCategory(CategoryDto categoryDto);

    void updateCategory(Long category_id, CategoryDto categoryDto);

    void deleteCategory(Long category_id);


}
