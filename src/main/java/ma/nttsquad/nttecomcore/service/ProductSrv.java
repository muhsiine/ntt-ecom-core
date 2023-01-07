package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.dto.ProductFilterDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductSrv {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId);

    List<ProductDto> getProductByCategoryId(Long categoryId);

    ProductDto updateProduct(Long product_id,ProductDto productDto);

    void deleteProduct(Long productId);

    ProductDto saveProduct(ProductDto productDto);

    List<ProductDto> filter(ProductFilterDto productFilterDto);

    Double maxPrice();
}
