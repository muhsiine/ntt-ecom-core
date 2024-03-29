package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.dto.CategoryDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.CategoriesMapper;
import ma.nttsquad.nttecomcore.mapper.CategoryByLangMapper;
import ma.nttsquad.nttecomcore.model.repository.CategoryByLangRepository;
import ma.nttsquad.nttecomcore.model.repository.CategoryRepository;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoriesSrvImpl implements CategoriesSrv {

    private final CategoryByLangRepository categoryByLangRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryByLangDto> getAllCategoriesByLang(LangCons langCode) {
        List<CategoryByLangDto> allCategories = categoryByLangRepository.findByLangCode(langCode)
                .stream()
                .map(CategoryByLangMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
        if(allCategories == null || allCategories.isEmpty()){
            throw new NttNotFoundException("There's no Categories in data base");
        }
        return allCategories;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> AllCategorytemDTO = categoryRepository.findAll()
                .stream()
                .map(CategoriesMapper.INSTANCE::entityToDto)
                .toList();
        if(AllCategorytemDTO == null || AllCategorytemDTO.isEmpty()){
            throw new NttNotFoundException("There's no categories in database");
        }
        return AllCategorytemDTO;
    }

    @Override
    public CategoryDto getCategoryById(Long category_id) {
        log.trace("{}", category_id);
        return categoryRepository.findById(category_id)
                .map(CategoriesMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no category with the id '%d'".formatted(category_id)));
    }

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        log.trace("save category: {}",categoryDto);
        return CategoriesMapper.INSTANCE.entityToDto(categoryRepository.save(CategoriesMapper.INSTANCE.dtoToEntity(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(Long category_id, CategoryDto categoryDto) {
        log.trace("update category: {} {}",category_id, categoryDto);
        CategoryDto category = getCategoryById(category_id);
        categoryDto.setId(category.getId());
        return CategoriesMapper.INSTANCE.entityToDto(categoryRepository.save(CategoriesMapper.INSTANCE.dtoToEntity(categoryDto)));
    }


    @Override
    public void deleteCategory(Long category_id) {
        log.trace("delete category: {}",category_id);
        categoryRepository.deleteById(category_id);
    }
}
