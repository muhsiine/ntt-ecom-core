package ma.nttsquad.nttecomcore.service.impl;

import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.dto.ProductDto;
import ma.nttsquad.nttecomcore.mapper.ProductsMapper;
import ma.nttsquad.nttecomcore.repository.ProductRepository;
import ma.nttsquad.nttecomcore.service.ProductSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class ProductSrvImpl implements ProductSrv {

    private ProductRepository productRepository;

    public ProductSrvImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> filter(String productName) {
        log.info("Product name: {}", productName);
        return productRepository.filter(productName).stream()
                .map(ProductsMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByName(String name) {
        return productRepository.findByName(name).stream()
                .map(ProductsMapper.INSTANCE::entityToDto)
                .collect(Collectors.toList());
    }
}
