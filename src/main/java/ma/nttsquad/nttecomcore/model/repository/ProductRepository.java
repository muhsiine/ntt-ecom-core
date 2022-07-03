package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.dto.ProductFilterDto;
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

    @Query(value = "FROM Product p WHERE 1 = 1 " +
            "AND (:#{#productFilterDto.name} IS NULL OR UPPER(p.name) LIKE UPPER(CONCAT('%',:#{#productFilterDto.name},'%'))) " +
            "AND (:#{#productFilterDto.description} IS NULL OR UPPER(p.description) LIKE (UPPER(CONCAT('%',:#{#productFilterDto.description},'%')) ))  " +
            "AND (:#{#productFilterDto.initialDate} IS NULL OR :#{#productFilterDto.endDate} IS NULL OR p.createdAt BETWEEN :#{#productFilterDto.initialDate} AND :#{#productFilterDto.endDate}) " +
            "AND (:#{#productFilterDto.categoryId} IS NULL OR p.category.id = :#{#productFilterDto.categoryId}) " +
            "AND (:#{#productFilterDto.price1} IS NULL OR p.price >= :#{#productFilterDto.price1})" +
            "AND (:#{#productFilterDto.price2} IS NULL OR p.price <= :#{#productFilterDto.price2}) ")
    List<Product> filter(@Param("productFilterDto")ProductFilterDto productFilterDto);

    @Query(value = "SELECT MAX(p.price) FROM Product p")
    Double maxPrice();

}
