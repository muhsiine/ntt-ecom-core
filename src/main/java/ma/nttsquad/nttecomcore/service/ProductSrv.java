package ma.nttsquad.nttecomcore.service;

import ma.nttsquad.nttecomcore.dto.ProductDto;

import java.util.List;

public interface ProductSrv {

    List<ProductDto> filter(String productName);

    List<ProductDto> findByName(String name);

}
