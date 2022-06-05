package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.ProductImageDto;

import java.util.List;

public interface ProductImageSrv {

     List<ProductImageDto> getImageByProduct(Long productId);
}
