package ma.nttsquad.nttecomcore.repository;

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

    @Query(value = "FROM Product p WHERE (UPPER(p.name) LIKE UPPER(CONCAT('%',:#{#productFilterDto.name},'%')))" +
            "OR UPPER(p.description) LIKE UPPER(CONCAT('%',:#{#productFilterDto.description},'%')) " +
            "OR p.createdAt BETWEEN :#{#productFilterDto.initialDate} AND :#{#productFilterDto.endDate} " +
            "OR  p.category.id = :#{#productFilterDto.categoryId} " +
            "OR p.price >= :#{#productFilterDto.price1} AND p.price <= :#{#productFilterDto.price2} " +
            "OR(:#{#productFilterDto.name} IS NULL AND :#{#productFilterDto.description} IS NULL " +
            "AND :#{#productFilterDto.initialDate} IS NULL AND :#{#productFilterDto.endDate} IS NULL " +
            "AND :#{#productFilterDto.categoryId} IS NULL " +
            "AND :#{#productFilterDto.price1} IS NULL AND :#{#productFilterDto.price2} IS NULL)")
    List<Product> filter(@Param("productFilterDto")ProductFilterDto productFilterDto);

}
