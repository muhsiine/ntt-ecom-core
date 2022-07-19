package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.dto.ProductFilterDto;
import ma.nttsquad.nttecomcore.mapper.ProductMapper;
import ma.nttsquad.nttecomcore.model.repository.ProductRepository;
import ma.nttsquad.nttecomcore.service.ProductSrv;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSrvImpl implements ProductSrv {

    final ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductMapper.INSTANCE::entityToDto).toList();
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return ProductMapper.INSTANCE.entityToDto(productRepository.findById(productId)
                .orElseThrow(() -> new NttNotFoundException("product with id '%d' not found".formatted(productId))));
    }

    @Override
    public List<ProductDto> getProductByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream().map(ProductMapper.INSTANCE::entityToDto).toList();
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        return ProductMapper.INSTANCE.entityToDto(productRepository.save(ProductMapper.INSTANCE.dtoToEntity(productDto)));
    }

    @Override
    public List<ProductDto> filter(ProductFilterDto productFilterDto) {
        return productRepository.filter(productFilterDto).stream().map(ProductMapper.INSTANCE::entityToDto).toList();
    }

    @Override
    public Double maxPrice() {
        return productRepository.maxPrice();
    }
}
