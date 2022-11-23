package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.ProductImageDto;
import ma.nttsquad.nttecomcore.dto.StatusDto;
import ma.nttsquad.nttecomcore.dto.UserDto;
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
        List<ProductImageDto> allProductImageDTO = productImageRepository.findAll()
                .stream()
                .map(ProductImageMapper.INSTANCE::entityToDto)
                .toList();
        if(allProductImageDTO == null || allProductImageDTO.isEmpty()){
            throw new NttNotFoundException("There's no products images in database");
        }
        return allProductImageDTO;

    }

    @Override
    public ProductImageDto getProductImageById(Long productImg_id) {
        log.trace("{}", productImg_id);
        return productImageRepository.findById(productImg_id)
                .map(ProductImageMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no product image with the id '%d'".formatted(productImg_id)));
    }

    @Override
    public ProductImageDto saveProductImage(ProductImageDto productImageDto) {
        log.trace("save product image: {}",productImageDto);
        return ProductImageMapper.INSTANCE.entityToDto(productImageRepository.save(ProductImageMapper.INSTANCE.dtoToEntity(productImageDto)));
    }

    @Override
    public ProductImageDto updateProductImage(Long productImg_id, ProductImageDto productImageDto) {
        log.trace("update product image: {} , {}",productImg_id, productImageDto);
        ProductImageDto prodImg = getProductImageById(productImg_id);
        productImageDto.setId(prodImg.getId());
        return ProductImageMapper.INSTANCE.entityToDto(productImageRepository.save(ProductImageMapper.INSTANCE.dtoToEntity(productImageDto)));
    }

    @Override
    public void deleteProductImage(Long productImg_id) {
        log.trace("delete product image: {}",productImg_id);
        productImageRepository.deleteById(productImg_id);
    }
}
