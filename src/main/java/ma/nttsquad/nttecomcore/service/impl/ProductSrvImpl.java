package ma.nttsquad.nttecomcore.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.exception.NttNotFoundException;
import ma.nttsquad.nttecomcore.mapper.ProductMapper;
import ma.nttsquad.nttecomcore.model.repository.ProductRepository;
import ma.nttsquad.nttecomcore.service.ProductSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductSrvImpl implements ProductSrv {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
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
        return productRepository.findByCategoryId(categoryId).stream()
                .map(ProductMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveProduct(ProductDto productDto) {
        log.trace("start save product: {}",productDto);
        productRepository.save(ProductMapper.INSTANCE.dtoToEntity(productDto));
        log.trace("end save product");
    }

    @Override
    public void updateProduct(Long product_id, ProductDto productDto) {
        log.trace("start update product: {} {}",product_id, productDto);
        ProductDto product = getProductById(product_id);
        productDto.setId(product.getId());
        productRepository.save(ProductMapper.INSTANCE.dtoToEntity(productDto));
        log.trace("end update product: {}",product_id);
    }

    @Override
    public void deleteProduct(Long product_id) {
        log.trace("start delete product: {}",product_id);
        productRepository.deleteById(product_id);
        log.trace("end delete product: {}",product_id);
    }
}
