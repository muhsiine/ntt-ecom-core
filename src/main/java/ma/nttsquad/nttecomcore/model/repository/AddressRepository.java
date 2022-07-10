package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
