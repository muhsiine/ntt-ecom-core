package ma.nttsquad.nttecomcore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.model.Product;
import ma.nttsquad.nttecomcore.service.ProductSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@Tag(name = "Product", description = "The Products API")
public class ProductCtrl {

    @Autowired
    private ProductSrv productSrv;

    @Operation(summary = "Find all Products", description = "Find all Products", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @GetMapping(value={"/all"})
    public List<ProductDto> getAllProducts(){

        return productSrv.getAllProducts();
    }

    @Operation(summary = "Find Product by Id", description = "Find Product by Id", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @GetMapping("/get/{product_id}")
    public ProductDto getProductById(@PathVariable("product_id") Long product_id){
        log.info("Id: {}", product_id);
        return productSrv.getProductById(product_id);
    }

    @Operation(summary = "Find Product by Category Id", description = "Find Product by Category Id", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @GetMapping("/category/{id}")
    public List<ProductDto> getProductByCategoryId(@PathVariable("id") Long categoryId){
        return productSrv.getProductByCategoryId(categoryId);
    }

    @Operation(summary = "Save Product", description = "Save Product", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @PostMapping("/save")
    public void saveProduct(@RequestBody ProductDto productDto){
        productSrv.saveProduct(productDto);
    }

    @Operation(summary = "update product", description = "Update Product", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{product_id}")
    public void updatProduct(@PathVariable(name = "product_id") Long product_id,@RequestBody ProductDto productDto){
        log.trace("{}", productDto);
        productSrv.updateProduct(product_id,productDto);
    }

    @Operation(summary = "delete product", description = "delete Product", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{product_id}")
    public void deleteProduct(@PathVariable("product_id") Long product_id){
        productSrv.deleteProduct(product_id);
    }

}
