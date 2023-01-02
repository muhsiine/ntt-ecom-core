package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}
