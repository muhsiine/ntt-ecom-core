package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.dto.StatusDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.ProductImageMapper;
import ma.nttsquad.nttecomcore.mapper.StatusMapper;
import ma.nttsquad.nttecomcore.mapper.UserMapper;
import ma.nttsquad.nttecomcore.model.repository.ProductImageRepository;
import ma.nttsquad.nttecomcore.service.ProductImageSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductImageSrvImpl implements ProductImageSrv {

    @Autowired
    private ProductImageRepository productImageRepository;


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

    @Override
    public List<ProductImageDto> getAllProductsImages() {
        return productImageRepository.findAll()
                .stream()
                .map(ProductImageMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductImageDto getProductImageById(Long productImg_id) {
        log.trace("{}", productImg_id);
        return productImageRepository.findById(productImg_id)
                .map(ProductImageMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no product image with the id '%d'".formatted(productImg_id)));
    }

    @Override
    public void saveProductImage(ProductImageDto productImageDto) {
        log.trace("start save product image: {}",productImageDto);
        productImageRepository.save(ProductImageMapper.INSTANCE.dtoToEntity(productImageDto));
        log.trace("end save product image");
    }

    @Override
    public void updateProductImage(Long productImg_id, ProductImageDto productImageDto) {
        log.trace("start update product image: {} {}",productImg_id, productImageDto);
        ProductImageDto prodImg = getProductImageById(productImg_id);
        productImageDto.setId(prodImg.getId());
        productImageRepository.save(ProductImageMapper.INSTANCE.dtoToEntity(productImageDto));
        log.trace("end update product image: {}",productImg_id);
    }

    @Override
    public void deleteProductImage(Long productImg_id) {
        log.trace("start delete product image: {}",productImg_id);
        productImageRepository.deleteById(productImg_id);
        log.trace("end delete product image: {}",productImg_id);
    }
}
