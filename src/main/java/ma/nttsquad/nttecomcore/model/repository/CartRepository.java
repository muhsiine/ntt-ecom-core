package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("FROM Cart c WHERE c.user.id = :user_id")
    Cart getCartByUser(@Param("user_id") Long user_id);

}
