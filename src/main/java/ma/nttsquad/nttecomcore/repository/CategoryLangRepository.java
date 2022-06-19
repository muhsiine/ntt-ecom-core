package ma.nttsquad.nttecomcore.repository;

import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.model.CategoryLang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryLangRepository extends JpaRepository<CategoryLang, Long> {
    List<CategoryLang>  findByLangCode(LangCons langCode);
}
