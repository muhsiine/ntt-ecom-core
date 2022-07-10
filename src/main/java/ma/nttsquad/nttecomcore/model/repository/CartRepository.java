package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findCartByUserId(Long userId);

}
