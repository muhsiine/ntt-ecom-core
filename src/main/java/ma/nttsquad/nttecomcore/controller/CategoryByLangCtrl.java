package ma.nttsquad.nttecomcore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.dto.CategoryDto;
import ma.nttsquad.nttecomcore.exception.NttBadRequestException;
import ma.nttsquad.nttecomcore.exception.records.ErrorResponse;
import ma.nttsquad.nttecomcore.service.CategoryByLangSrv;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/categoriesByLang")
@Tag(name = "CategoryByLang", description = "The Categories By Lang API")
public class CategoryByLangCtrl {

    final  CategoryByLangSrv categoryByLangSrv;

    @Operation(summary = "Find all categories by lang", description = "Find all Categories By Lang", tags = "CategoryByLang", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CategoryByLangDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/all")
    public ResponseEntity<List<CategoryByLangDto>> getAll(){
        return ResponseEntity.ok().body(categoryByLangSrv.getAllCategoriesByLang());
    }

    @Operation(summary = "Find category by id", description = "Find Category By Id", tags = "CategoryByLang", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CategoryByLangDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryByLangDto> getCategoryById(@PathVariable(name = "category_id") Long category_id) {return ResponseEntity.ok().body(categoryByLangSrv.getCategoryById(category_id));}
    @Operation(summary = "Add new Category by lang", description = "Add new category by lang", tags = "Category", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/save")
    public ResponseEntity<CategoryByLangDto> saveCategoryByLang(@RequestBody CategoryByLangDto categoryByLangDto) throws Exception {
        log.trace("{}", categoryByLangDto);
        try{
            return ResponseEntity.ok().body(categoryByLangSrv.saveCategoryByLang(categoryByLangDto));
        }catch(RuntimeException ex){
            throw new NttBadRequestException(ex.getLocalizedMessage());
        }catch(Exception ex){
            throw new Exception(ex.getLocalizedMessage());
        }
    }
    @Operation(summary = "Update Category by lang", description = "Update category by lang", tags = "Category", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/edit/{category_id}")
    public ResponseEntity<CategoryByLangDto> updateCategoryByLang(@PathVariable(name = "category_id") Long category_id,@RequestBody CategoryByLangDto categoryByLangDto) throws Exception {
        log.trace("{}", categoryByLangDto);
        try{
            return ResponseEntity.ok().body(categoryByLangSrv.updateCategoryByLang(category_id,categoryByLangDto));
        }catch(RuntimeException ex){
            throw new NttBadRequestException(ex.getLocalizedMessage());
        }catch(Exception ex){
            throw new Exception(ex.getLocalizedMessage());
        }
    }

    @Operation(summary = "Delete Category by lang", description = "Delete category by lang", tags = "Category", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CategoryDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/delete/{category_id}")
    public ResponseEntity<Void> deleteCategoryByLang(@PathVariable(name = "category_id") Long category_id) throws Exception {
        log.trace("{}", category_id);
        try{
            categoryByLangSrv.deleteCategoryByLang(category_id);
            return ResponseEntity.noContent().build();
        }catch(RuntimeException ex){
            throw new NttBadRequestException(ex.getLocalizedMessage());
        }catch(Exception ex){
            throw new Exception(ex.getLocalizedMessage());
        }
    }
}
