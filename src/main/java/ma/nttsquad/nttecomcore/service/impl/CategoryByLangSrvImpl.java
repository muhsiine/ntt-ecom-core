package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.dto.UserDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.CategoryByLangMapper;
import ma.nttsquad.nttecomcore.mapper.UserMapper;
import ma.nttsquad.nttecomcore.model.repository.CategoryByLangRepository;
import ma.nttsquad.nttecomcore.service.CategoryByLangSrv;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryByLangSrvImpl implements CategoryByLangSrv {

    CategoryByLangRepository categoryByLangRepository;

    @Override
    public List<CategoryByLangDto> getAllCategoriesByLang() {
        List<CategoryByLangDto> allCategoryByLangDTO = categoryByLangRepository.findAll()
                .stream()
                .map(CategoryByLangMapper.INSTANCE::entityToDto)
                .toList();
        if(allCategoryByLangDTO == null || allCategoryByLangDTO.isEmpty()){
            throw new NttNotFoundException("There's no categories in database");
        }
        return allCategoryByLangDTO;
    }

    @Override
    public CategoryByLangDto getCategoryById(Long categoryByLang_id) {
        log.trace("{}", categoryByLang_id);
        return categoryByLangRepository.findById(categoryByLang_id)
                .map(CategoryByLangMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no category with the id '%d'".formatted(categoryByLang_id)));

    }

    @Override
    public CategoryByLangDto saveCategoryByLang(CategoryByLangDto categoryByLangDto) {
        log.trace("save category: {}",categoryByLangDto);
        return CategoryByLangMapper.INSTANCE.entityToDto(categoryByLangRepository.save(CategoryByLangMapper.INSTANCE.dtoToEntity(categoryByLangDto)));
    }

    @Override
    public CategoryByLangDto updateCategoryByLang(Long categoryByLang_id, CategoryByLangDto categoryByLangDto) {
        log.trace("update category: {} {}",categoryByLang_id, categoryByLangDto);
        CategoryByLangDto category = getCategoryById(categoryByLang_id);
        categoryByLangDto.setId(category.getId());
        return CategoryByLangMapper.INSTANCE.entityToDto(categoryByLangRepository.save(CategoryByLangMapper.INSTANCE.dtoToEntity(categoryByLangDto)));
    }

    @Override
    public void deleteCategoryByLang(Long categoryByLang_id) {
        log.trace("delete category: {}",categoryByLang_id);
        categoryByLangRepository.deleteById(categoryByLang_id);
    }
}
