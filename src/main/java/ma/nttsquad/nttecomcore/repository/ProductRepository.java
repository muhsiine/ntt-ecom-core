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

    @Query(value = "FROM Product p WHERE (UPPER(p.name) LIKE UPPER(CONCAT('%',:name,'%')))" +
            "OR UPPER(p.description) LIKE UPPER(CONCAT('%',:description,'%')) " +
            "OR p.createdAt BETWEEN :initialDate AND :endDate OR  p.category.id = :categoryId OR p.price >= :price1 AND p.price <= :price2 " +
            "OR(:name IS NULL AND :description IS NULL AND :initialDate IS NULL AND :endDate IS NULL AND :categoryId IS NULL " +
            "AND :price1 IS NULL AND :price2 IS NULL)")
    List<Product> filter(@Param("name") String name, @Param("description") String description,
                         @Param("initialDate") LocalDateTime initialDate, @Param("endDate") LocalDateTime endDate,
                         @Param("categoryId") Long categoryId, @Param("price1") Double price1,
                         @Param("price2") Double price2);

}
