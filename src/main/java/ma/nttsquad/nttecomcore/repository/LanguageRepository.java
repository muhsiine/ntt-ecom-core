package ma.nttsquad.nttecomcore.repository;

import ma.nttsquad.nttecomcore.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
