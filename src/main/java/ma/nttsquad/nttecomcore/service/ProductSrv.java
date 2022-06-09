package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.ProductDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductSrv {

    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long productId);

    List<ProductDto> getProductByCategoryId(Long categoryId);

    void saveProduct(ProductDto productDto);

    List<ProductDto> filter(String name, String description, LocalDateTime initialDate, LocalDateTime endDate,
                            Long categoryId, Double price1, Double price2);

}
