package ma.nttsquad.nttecomcore.controller;


import ma.nttsquad.nttecomcore.dto.CategoryDto;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryCtrl {

    private final CategoriesSrv categorySrv;

    public CategoryCtrl(CategoriesSrv categorySrv) {
        this.categorySrv = categorySrv;
    }

    @GetMapping("/all")
    public List<CategoryDto> getAllCategories() {
        return categorySrv.getAllCategories();
    }
}
