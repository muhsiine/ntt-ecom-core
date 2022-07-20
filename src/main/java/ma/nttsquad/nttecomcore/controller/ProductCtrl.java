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
import ma.nttsquad.nttecomcore.exception.handler.NttExceptionHandler;
import ma.nttsquad.nttecomcore.model.Product;
import ma.nttsquad.nttecomcore.service.ProductSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Product", description = "The Products API")
@Slf4j
public class ProductCtrl {

    private final ProductSrv productSrv;

    @Operation(summary = "Find all Products", description = "Return a list of a products", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping(value={"/all"})
    public List<ProductDto> getAllProducts(){

        return productSrv.getAllProducts();
    }

    @Operation(summary = "Find Product", description = "Find Product by Id", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping("/findById/{id}")
    public ProductDto getProductById(@PathVariable("id") Long productId){
        log.info("Id: {}", productId);
        return productSrv.getProductById(productId);
    }

    @Operation(summary = "Find Product", description = "Find Product by Category Id", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @GetMapping("/category/{id}")
    public List<ProductDto> getProductByCategoryId(@PathVariable("id") Long categoryId){
        return productSrv.getProductByCategoryId(categoryId);
    }

    @Operation(summary = "Add Product", description = "Add new Product", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class))),
            @ApiResponse(responseCode = "500", description = "Error", content = @Content(schema = @Schema(implementation = NttExceptionHandler.class)))
    })
    @PostMapping("/save")
    public void saveProduct(@RequestBody ProductDto productDto){
        productSrv.saveProduct(productDto);
    }

}
