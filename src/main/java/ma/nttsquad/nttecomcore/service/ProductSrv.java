package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.dto.ProductFilterDto;

import java.util.List;

public interface ProductSrv {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId);

    List<ProductDto> getProductByCategoryId(Long categoryId);

    void saveProduct(ProductDto productDto);

    List<ProductDto> filter(ProductFilterDto productFilterDto);

    Double maxPrice();

}
