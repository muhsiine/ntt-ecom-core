package ma.nttsquad.nttecomcore.controller;

import lombok.extern.slf4j.Slf4j;
import ma.nttsquad.nttecomcore.model.Product;
import ma.nttsquad.nttecomcore.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductCtrl {

    private final ProductRepository productRepository;

    public ProductCtrl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value={"/all"})
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long productId){
        log.info("Id: {}", productId);
        return productRepository.findById(productId)
                .orElseThrow( () -> new RuntimeException("Product with id: '" + productId + "' not found!"));
    }

    @GetMapping("/category/{id}")
    public List<Product> getProductByCategoryId(@PathVariable("id") Long categoryId){
        return productRepository.findByCategoryId(categoryId);
    }

    @PostMapping("/save")
    public void saveProduct(@RequestBody Product product){
        productRepository.save(product);
    }

}
