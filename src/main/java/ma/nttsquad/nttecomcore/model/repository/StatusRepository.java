package ma.nttsquad.nttecomcore.model.repository;

import ma.nttsquad.nttecomcore.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
