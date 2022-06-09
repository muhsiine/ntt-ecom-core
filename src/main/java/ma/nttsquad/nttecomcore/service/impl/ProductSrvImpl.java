package ma.nttsquad.nttecomcore.service.impl;

import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.mapper.ProductMapper;
import ma.nttsquad.nttecomcore.repository.ProductRepository;
import ma.nttsquad.nttecomcore.service.ProductSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSrvImpl implements ProductSrv {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return ProductMapper.INSTANCE
                .entityToDto(productRepository.findById(productId).get());
    }

    @Override
    public List<ProductDto> getProductByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        productRepository.save(ProductMapper.INSTANCE.dtoToEntity(productDto));
    }

    @Override
    public List<ProductDto> filter(String name, String description, LocalDateTime initialDate, LocalDateTime endDate,
                                   Long categoryId, Double price1, Double price2) {
        return productRepository.filter(name, description, initialDate, endDate, categoryId, price1, price2).stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }
}
