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
import ma.nttsquad.nttecomcore.dto.CartDto;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.exception.NttBadRequestException;
import ma.nttsquad.nttecomcore.exception.records.ErrorResponse;
import ma.nttsquad.nttecomcore.dto.ProductFilterDto;
import ma.nttsquad.nttecomcore.model.Product;
import ma.nttsquad.nttecomcore.service.ProductSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
@Tag(name = "Product", description = "The Products API")
public class ProductCtrl {

    final ProductSrv productSrv;

    @Operation(summary = "Find all Products", description = "Find all Products", tags = "Product", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping(value={"/all"})
    public ResponseEntity<List<ProductDto>> getAllProducts(){

        return ResponseEntity.ok().body(productSrv.getAllProducts());
    }

    @Operation(summary = "Find Product by Id", description = "Find Product by Id", tags = "Product", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId){
        log.info("Id: {}", productId);
        return ResponseEntity.ok().body(productSrv.getProductById(productId));
    }

    @Operation(summary = "Find Product by Category Id", description = "Find Product by Category Id", tags = "Product", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> getProductByCategoryId(@PathVariable("id") Long categoryId){
        return ResponseEntity.ok().body(productSrv.getProductByCategoryId(categoryId));
    }

    @Operation(summary = "Save Product", description = "Save Product", tags = "Product", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/save")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) throws Exception {
        try{
            return ResponseEntity.ok().body(productSrv.saveProduct(productDto));
        }catch(RuntimeException ex){
            throw new NttBadRequestException(ex.getLocalizedMessage());
        }catch(Exception ex){
            throw new Exception(ex.getLocalizedMessage());
        }
    }

    @Operation(summary = "filer Product", description = "filer Product", tags = "Product", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/filter")
    public ResponseEntity<List<ProductDto>> filter(@RequestBody(required = false) ProductFilterDto productFilterDto){
        return ResponseEntity.ok(productSrv.filter(productFilterDto));
    }

    @Operation(summary = "get max Price", description = "get max Price", tags = "Product", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Double.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Bad GATEWAY", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/maxPrice")
    public ResponseEntity<Double> maxPrice(){
        return ResponseEntity.ok(productSrv.maxPrice());
    }
}
