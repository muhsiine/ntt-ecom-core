package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.dto.StatusDto;

import java.util.List;

public interface ProductImageSrv {

     List<ProductImageDto> getImageByProduct(Long productImg_id);

     List<ProductImageDto> getAllProductsImages();

     ProductImageDto getProductImageById(Long productImg_id);

     void saveProductImage(ProductImageDto productImageDto);

     void updateProductImage(Long productImg_id, ProductImageDto productImageDto);

     void deleteProductImage(Long productImg_id);
}
