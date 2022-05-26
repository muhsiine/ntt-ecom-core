package ma.nttsquad.nttecomcore.repository;

import ma.nttsquad.nttecomcore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
