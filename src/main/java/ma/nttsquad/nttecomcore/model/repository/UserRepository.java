package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}