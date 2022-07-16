package ma.nttsquad.nttecomcore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.model.Product;
import ma.nttsquad.nttecomcore.service.ProductImageSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/images")
@Tag(name = "Image", description = "The Product Image API")
public class ProductImageCtrl {

    @Autowired
    private ProductImageSrv productImageSrv;

    @Operation(summary = "Find Images by ProductId", description = "Find Images by ProductId", tags = "Image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    })
    @GetMapping("/product/{id}")
    public List<ProductImageDto> getImageByProduct(@PathVariable("id") Long productId){
        return productImageSrv.getImageByProduct(productId);
    }

    @Operation(summary = "Find all products images", description = "Find all Products Images", tags = "Image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductImageDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/all")
    public List<ProductImageDto> getAll(){
        return productImageSrv.getAllProductsImages();
    }

    @Operation(summary = "Find product image by id", description = "Find Procut Image By Id", tags = "Image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductImageDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @GetMapping("/get/{productImg_id}")
    public ProductImageDto getProductImageById(@PathVariable(name = "productImg_id") Long productImg_id) {return productImageSrv.getProductImageById(productImg_id);}

    @Operation(summary = "add new product image", description = "Add New Product Image", tags = "Image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductImageDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/save")
    public void saveProductImage(@RequestBody ProductImageDto productImageDto){
        log.trace("{}", productImageDto);
        productImageSrv.saveProductImage(productImageDto);
    }

    @Operation(summary = "update product image", description = "Update Product Image", tags = "Image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductImageDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/edit/{productImg_id}")
    public void updateProductImage(@PathVariable(name = "productImg_id") Long productImg_id,@RequestBody ProductImageDto productImageDto){
        log.trace("{}", productImageDto);
        productImageSrv.updateProductImage(productImg_id,productImageDto);
    }

    @Operation(summary = "delete product image", description = "delete Product Image", tags = "Image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductImageDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(description = "HTTP status error code", example = "400")))
    }
    )
    @PostMapping("/delete/{productImg_id}")
    public void removeProductImage(@PathVariable(name = "productImg_id") Long productImg_id){
        log.trace("{}", productImg_id);
        productImageSrv.deleteProductImage(productImg_id);
    }
}
