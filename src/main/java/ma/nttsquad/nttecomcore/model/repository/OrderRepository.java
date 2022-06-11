package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.Order;
import ma.nttsquad.nttecomcore.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
