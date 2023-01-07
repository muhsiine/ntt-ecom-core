package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
