package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.ProductDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductSrv {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId);

    List<ProductDto> getProductByCategoryId(Long categoryId);

    void saveProduct(ProductDto productDto);

    List<ProductDto> filter(String productName);

    List<ProductDto> findByName(String name);

}
