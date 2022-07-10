package ma.nttsquad.nttecomcore.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.dto.CategoryDto;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "The Categories API")
@RequiredArgsConstructor
public class CategoryCtrl {


    private final CategoriesSrv categorySrv;

    @Operation(summary = "Find all Categories", description = "Find all Categories", tags = "Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Category.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @GetMapping("/all")
    public List<CategoryByLangDto> getAllCategoriesByLang(@RequestParam("lang") LangCons langCode) {
        return categorySrv.getAllCategoriesByLang(langCode);
    }

    @Operation(summary = "Find category by id", description = "Find Category By Id", tags = "Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{category_id}")
    public CategoryDto getCategoryById(@PathVariable(name = "category_id") Long category_id) {return categorySrv.getCategoryById(category_id);}

    @Operation(summary = "add new category", description = "Add New Category", tags = "Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto
                    .class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveCategory(@RequestBody CategoryDto categoryDto){
        log.trace("{}", categoryDto);
        categorySrv.saveCategory(categoryDto);
    }

    @Operation(summary = "update category", description = "Update Category", tags = "Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{category_id}")
    public void updateCategory(@PathVariable(name = "category_id") Long category_id,@RequestBody CategoryDto categoryDto){
        log.trace("{}", categoryDto);
        categorySrv.updateCategory(category_id,categoryDto);
    }

    @Operation(summary = "delete category", description = "delete Category", tags = "Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{category_id}")
    public void removeCategory(@PathVariable(name = "category_id") Long category_id){
        log.trace("{}", category_id);
        categorySrv.deleteCategory(category_id);
    }
}
