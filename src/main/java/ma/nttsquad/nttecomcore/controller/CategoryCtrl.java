package ma.nttsquad.nttecomcore.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.nttsquad.nttecomcore.dto.CategoryDto;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "The categories API")
public class CategoryCtrl {

    private final CategoriesSrv categorySrv;

    public CategoryCtrl(CategoriesSrv categorySrv) {
        this.categorySrv = categorySrv;
    }

    @Operation(summary = "Find all Categories", description = "Find all Categories", tags = "Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Category.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @GetMapping("/all")
    public List<CategoryDto> getAllCategories() {
        return categorySrv.getAllCategories();
    }
}
