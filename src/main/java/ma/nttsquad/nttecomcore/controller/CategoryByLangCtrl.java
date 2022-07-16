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
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.service.CategoryByLangSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/categoriesByLang")
@Tag(name = "CategoryByLang", description = "The Categories By Lang API")
public class CategoryByLangCtrl {

    @Autowired
    final CategoryByLangSrv categoryByLangSrv;

    @Operation(summary = "Find all categories by lang", description = "Find all Categories By Lang", tags = "CategoryByLang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryByLangDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<CategoryByLangDto> getAll(){
        return categoryByLangSrv.getAllCategoriesByLang();
    }

    @Operation(summary = "Find category by id", description = "Find Category By Id", tags = "CategoryByLang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryByLangDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{category_id}")
    public CategoryByLangDto getCategoryById(@PathVariable(name = "category_id") Long category_id) {return categoryByLangSrv.getCategoryById(category_id);}

    @Operation(summary = "add new category by lang", description = "Add New CategoryByLang", tags = "CategoryByLang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryByLangDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveCategoryByLang(@RequestBody CategoryByLangDto categoryByLangDto){
        log.trace("{}", categoryByLangDto);
        categoryByLangSrv.saveCategoryByLang(categoryByLangDto);
    }

    @Operation(summary = "update category by lang", description = "Update CategoryByLang", tags = "CategoryByLang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryByLangDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{category_id}")
    public void updateCategoryByLang(@PathVariable(name = "category_id") Long category_id,@RequestBody CategoryByLangDto categoryByLangDto){
        log.trace("{}", categoryByLangDto);
        categoryByLangSrv.updateCategoryByLang(category_id,categoryByLangDto);
    }

    @Operation(summary = "delete category by lang", description = "delete CategoryByLang", tags = "CategoryByLang")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryByLangDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{category_id}")
    public void removeCategoryByLang(@PathVariable(name = "category_id") Long category_id){
        log.trace("{}", category_id);
        categoryByLangSrv.deleteCategoryByLang(category_id);
    }
}
