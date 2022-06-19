package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.model.CategoryByLang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryByLangRepository extends JpaRepository<CategoryByLang, Long> {
    List<CategoryByLang>  findByLangCode(LangCons langCode);
}
