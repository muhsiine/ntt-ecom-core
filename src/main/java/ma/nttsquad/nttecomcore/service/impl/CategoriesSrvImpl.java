package ma.nttsquad.nttecomcore.service.impl;

import ma.nttsquad.nttecomcore.mapper.CategoriesMapper;
import ma.nttsquad.nttecomcore.dto.CategoryDto;
import ma.nttsquad.nttecomcore.model.repository.CategoryRepository;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesSrvImpl implements CategoriesSrv {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoriesMapper.INSTANCE::entityToDto
                ).collect(Collectors.toList());

    }
}
