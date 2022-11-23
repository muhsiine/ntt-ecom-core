package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.dto.ProductFilterDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.ProductMapper;
import ma.nttsquad.nttecomcore.model.repository.ProductRepository;
import ma.nttsquad.nttecomcore.service.ProductSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductSrvImpl implements ProductSrv {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        List<ProductDto> allProducts = productRepository
                                            .findAll()
                                            .stream()
                                            .map(ProductMapper.INSTANCE::entityToDto)
                                            .toList();
        if(allProducts == null || allProducts.isEmpty()){
            throw new NttNotFoundException("There's no products in database");
        }
        return allProducts;
    }

    @Override
    public ProductDto getProductById(Long product_id) {
        log.trace("{}", product_id);
        return productRepository.findById(product_id)
                .map(ProductMapper.INSTANCE::entityToDto)
                .orElseThrow(() -> new NttNotFoundException("There's no product with the id '%d'".formatted(product_id)));
    }

    @Override
    public List<ProductDto> getProductByCategoryId(Long categoryId) {
        List<ProductDto> allProducts = productRepository
                .findByCategoryId(categoryId)
                .stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .toList();
        if(allProducts == null || allProducts.isEmpty()){
            throw new NttNotFoundException("There's no Products belonging the Category with the id '%d'".formatted(categoryId));
        }
        return allProducts;
    }


    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        log.trace("save product: {}",productDto);
        return ProductMapper.INSTANCE.entityToDto(productRepository.save(ProductMapper.INSTANCE.dtoToEntity(productDto)));
    }

    @Override
    public ProductDto updateProduct(Long product_id, ProductDto productDto) {
        log.trace("update product: {} , {}",product_id, productDto);
        ProductDto product = getProductById(product_id);
        productDto.setId(product.getId());
        return ProductMapper.INSTANCE.entityToDto(productRepository.save(ProductMapper.INSTANCE.dtoToEntity(productDto)));
    }

    @Override
    public void deleteProduct(Long product_id) {
        log.trace("delete product: {}",product_id);
        productRepository.deleteById(product_id);
    }
    @Override
    public List<ProductDto> filter(ProductFilterDto productFilterDto) {
        List<ProductDto> filtredProducts = productRepository
                .filter(productFilterDto)
                .stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .toList();
        if(filtredProducts == null || filtredProducts.isEmpty()){
            throw new NttNotFoundException("There's no Products for your filtre");
        }
        return filtredProducts;
    }

    @Override
    public Double maxPrice() {
        return productRepository.maxPrice();
    }
}
