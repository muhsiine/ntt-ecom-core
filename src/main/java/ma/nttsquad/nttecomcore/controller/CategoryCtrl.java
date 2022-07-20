package ma.nttsquad.nttecomcore.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.dto.CategoryByLangDto;
import ma.nttsquad.nttecomcore.exception.handler.NttExceptionHandler;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.service.CategoriesSrv;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "The categories API")
public class CategoryCtrl {

    private final CategoriesSrv categorySrv;

    @Operation(summary = "Find all Categories", description = "Return a list of categories", tags = "Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CategoryByLangDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping("/all")
    public List<CategoryByLangDto> getAllCategoriesByLang(@RequestParam("lang") LangCons langCode) {
        return categorySrv.getAllCategoriesByLang(langCode);
    }
}
