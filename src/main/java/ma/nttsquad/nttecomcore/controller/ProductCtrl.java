package ma.nttsquad.nttecomcore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.model.Product;
import ma.nttsquad.nttecomcore.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
@Tag(name = "Product", description = "The Products API")
public class ProductCtrl {

    private final ProductRepository productRepository;

    public ProductCtrl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Operation(summary = "Find all Products", description = "Find all Products", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @GetMapping(value={"/all"})
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Operation(summary = "Find Product by Id", description = "Find Product by Id", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long productId){
        log.info("Id: {}", productId);
        return productRepository.findById(productId)
                .orElseThrow( () -> new NttNotFoundException("Product with id: '%d' not found!".formatted(productId)));
    }

    @Operation(summary = "Find Product by Category Id", description = "Find Product by Category Id", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @GetMapping("/category/{id}")
    public List<Product> getProductByCategoryId(@PathVariable("id") Long categoryId){
        return productRepository.findByCategoryId(categoryId);
    }

    @Operation(summary = "Save Product", description = "Save Product", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @PostMapping("/save")
    public void saveProduct(@RequestBody Product product){
        productRepository.save(product);
    }

}
