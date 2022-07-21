package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.ProductImageMapper;
import ma.nttsquad.nttecomcore.model.repository.ProductImageRepository;
import ma.nttsquad.nttecomcore.service.ProductImageSrv;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageSrvImpl implements ProductImageSrv {

    private final ProductImageRepository productImageRepository;


    @Override
    public List<ProductImageDto> getImageByProduct(Long productId) {
        List<ProductImageDto> allImages= productImageRepository.findByProductId(productId)
                .stream()
                .map(ProductImageMapper.INSTANCE::entityToDto)
                .toList();
        if(allImages == null || allImages.isEmpty()){
            throw new NttNotFoundException("There's no Images belonging the Product with the id '%d'".formatted(productId));
        }
        return allImages;
    }
}
