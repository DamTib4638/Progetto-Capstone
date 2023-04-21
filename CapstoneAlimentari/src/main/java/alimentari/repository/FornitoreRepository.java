package alimentari.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alimentari.entity.Fornitore;

@Repository
public interface FornitoreRepository extends JpaRepository<Fornitore, Long> {
	
	Boolean existsByEmail(String email);
	
	Optional<Fornitore> findByEmail(String email);

}
