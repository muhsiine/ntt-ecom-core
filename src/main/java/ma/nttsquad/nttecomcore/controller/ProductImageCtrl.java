package ma.nttsquad.nttecomcore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.exception.NttBadRequestException;
import ma.nttsquad.nttecomcore.exception.records.ErrorResponse;
import ma.nttsquad.nttecomcore.service.ProductImageSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@Tag(name = "Image", description = "The Product Image API")
public class ProductImageCtrl {

    final  ProductImageSrv productImageSrv;

    @Operation(summary = "Find Images by ProductId", description = "Find Images by ProductId", tags = "Image", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductImageDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/product/{id}")
    ResponseEntity<List<ProductImageDto>> getImageByProduct(@PathVariable("id") Long productId){
        return ResponseEntity.ok().body(productImageSrv.getImageByProduct(productId));
    }

    @Operation(summary = "Find all products images", description = "Find all products images", tags = "Image", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProductImageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/all")
    public List<ProductImageDto> getAll(){
        return (List<ProductImageDto>) ResponseEntity.ok().body(productImageSrv.getAllProductsImages());
    }

    @Operation(summary = "Find product image by id", description = "Find product image by id", tags = "Image", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProductImageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{productImg_id}")
    public ResponseEntity<ProductImageDto> getProductImageById(@PathVariable(name = "productImg_id") Long productImg_id) {return ResponseEntity.ok().body(productImageSrv.getProductImageById(productImg_id));}

    @Operation(summary = "Add new Product Image", description = "Add new product image", tags = "Image", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProductImageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/save")
    public ResponseEntity<ProductImageDto> saveProductImage(@RequestBody ProductImageDto productImageDto) throws Exception {
        log.trace("{}", productImageDto);
        try{
            return ResponseEntity.ok().body(productImageSrv.saveProductImage(productImageDto));
        }catch(RuntimeException ex){
            throw new NttBadRequestException(ex.getLocalizedMessage());
        }catch(Exception ex){
            throw new Exception(ex.getLocalizedMessage());
        }
    }

    @Operation(summary = "Update Product Image", description = "Update product image", tags = "Image", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProductImageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/edit/{productImg_id}")
    public ResponseEntity<ProductImageDto> updateProductImage(@PathVariable(name = "productImg_id") Long productImg_id,@RequestBody ProductImageDto productImageDto) throws Exception {
        log.trace("{}", productImageDto);
        try{
            return ResponseEntity.ok().body(productImageSrv.updateProductImage(productImg_id,productImageDto));
        }catch(RuntimeException ex){
            throw new NttBadRequestException(ex.getLocalizedMessage());
        }catch(Exception ex){
            throw new Exception(ex.getLocalizedMessage());
        }
    }

    @Operation(summary = "Delete Product Image", description = "Delete product image", tags = "Image", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProductImageDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/delete/{productImg_id}")
    public void removeProductImage(@PathVariable(name = "productImg_id") Long productImg_id) throws Exception {
        log.trace("{}", productImg_id);
        try{
            productImageSrv.deleteProductImage(productImg_id);
        }catch(RuntimeException ex){
            throw new NttBadRequestException(ex.getLocalizedMessage());
        }catch(Exception ex){
            throw new Exception(ex.getLocalizedMessage());
        }

    }
}
