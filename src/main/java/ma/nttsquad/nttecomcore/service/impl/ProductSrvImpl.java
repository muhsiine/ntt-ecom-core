package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.dto.ProductFilterDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
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
        List<ProductDto> allProducts = productRepository
                .findAll()
                .stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .toList();
        if (allProducts.isEmpty()) {
            throw new NttNotFoundException("There's no Products in data base");
        }
        return allProducts;
    }

    @Override
    public ProductDto getProductById(Long productId) {
        return ProductMapper.INSTANCE.entityToDto(productRepository.findById(productId)
                .orElseThrow(() -> new NttNotFoundException("product with id '%d' is not found".formatted(productId))));
    }

    @Override
    public List<ProductDto> getProductByCategoryId(Long categoryId) {
        List<ProductDto> allProducts = productRepository
                .findByCategoryId(categoryId)
                .stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .toList();
        if (allProducts.isEmpty()) {
            throw new NttNotFoundException("There's no Products belonging the Category with the id '%d'".formatted(categoryId));
        }
        return allProducts;
    }

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        try {
            return ProductMapper.INSTANCE.entityToDto(productRepository.save(ProductMapper.INSTANCE.dtoToEntity(productDto)));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getLocalizedMessage());
        }
    }

    @Override
    public List<ProductDto> filter(ProductFilterDto productFilterDto) {
        List<ProductDto> filtredProducts = productRepository
                .filter(productFilterDto)
                .stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .toList();
        if (filtredProducts.isEmpty()) {
            throw new NttNotFoundException("There's no Products for your filtre");
        }
        return filtredProducts;
    }

    @Override
    public Double maxPrice() {
        return productRepository.maxPrice();
    }
}
