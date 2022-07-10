package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.CartItemDto;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.model.Category;

import java.util.List;

public interface CategoryByLangSrv {

    List<CategoryByLangDto> getAllCategoriesByLang();

    CategoryByLangDto getCategoryById(Long categoryByLang_id);

    void saveCategoryByLang(CategoryByLangDto categoryByLangDto);

    void updateCategoryByLang(Long categoryByLang_id, CategoryByLangDto categoryByLangDto);

    void deleteCategoryByLang(Long categoryByLang_id);
}
