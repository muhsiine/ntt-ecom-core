package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
