package ma.nttsquad.nttecomcore.repository;

import ma.nttsquad.nttecomcore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
}
