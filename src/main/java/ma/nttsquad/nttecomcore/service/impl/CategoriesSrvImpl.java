package ma.nttsquad.nttecomcore.service.impl;

import ma.nttsquad.nttecomcore.CategoriesMapper;
import ma.nttsquad.nttecomcore.dto.CategoryDto;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.repository.CategoryDao;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesSrvImpl implements CategoriesSrv {

    private final CategoryDao categoryDao;

    public CategoriesSrvImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryDao.findAll().stream()
                .map(CategoriesMapper.INSTANCE::entityToDto
        ).collect(Collectors.toList());

    }
}
