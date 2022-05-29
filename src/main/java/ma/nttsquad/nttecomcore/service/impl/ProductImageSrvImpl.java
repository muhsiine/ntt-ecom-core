package ma.nttsquad.nttecomcore.service.impl;

import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.mapper.ProductImageMapper;
import ma.nttsquad.nttecomcore.repository.ProductImageRepository;
import ma.nttsquad.nttecomcore.service.ProductImageSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductImageSrvImpl implements ProductImageSrv {

    @Autowired
    private ProductImageRepository productImageRepository;


    @Override
    public List<ProductImageDto> getImageByProduct(Long productId) {
        return productImageRepository.findByProductId(productId)
                .stream()
                .map(ProductImageMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }
}
