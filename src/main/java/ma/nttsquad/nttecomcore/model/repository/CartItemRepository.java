package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
