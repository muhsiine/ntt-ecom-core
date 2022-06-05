package ma.nttsquad.nttecomcore.repository;

import ma.nttsquad.nttecomcore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId(Long categoryId);

    List<Product> findByName(String name);

    @Query(value = "FROM Product p WHERE UPPER(p.name) LIKE UPPER(CONCAT('%',:productName,'%')) OR :productName IS NULL")
    List<Product> filter(@Param("productName") String productName);

    @Query(value = "FROM Product p WHERE UPPER(p.description) LIKE UPPER(CONCAT('%',:productDescription,'%'))")
    List<Product> findProductsByDescription(@Param("productDescription") String productDescription);

    @Query(value = "FROM Product p WHERE p.createdAt BETWEEN :initialDate AND :endDate")
    List<Product> findProductsByDate(@Param("initialDate") LocalDateTime initialdate, @Param("endDate") LocalDateTime endDate);

    @Query(value = "FROM Product p WHERE p.category.id = :categoryId")
    List<Product> findProductsByCategory(@Param("categoryId") Integer categoryId);

    @Query(value = "FROM Product p WHERE p.price >= :price1 AND p.price <= :price2")
    List<Product> findProductByPrice(@Param("price1") Double price1, @Param("price2") Double price2);
}
