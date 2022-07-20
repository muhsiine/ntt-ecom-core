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
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.exception.handler.NttExceptionHandler;
import ma.nttsquad.nttecomcore.model.Product;
import ma.nttsquad.nttecomcore.service.ProductImageSrv;
import ma.nttsquad.nttecomcore.service.ProductSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@Tag(name = "Image", description = "The Product Image API")
public class ProductImageCtrl {

    private final ProductImageSrv productImageSrv;

    @Operation(summary = "Find Images", description = "Find all Images by ProductId", tags = "Image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductImageDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping("/{id}")
    public List<ProductImageDto> getImageByProduct(@PathVariable("id") Long productId){
        return productImageSrv.getImageByProduct(productId);
    }
}
