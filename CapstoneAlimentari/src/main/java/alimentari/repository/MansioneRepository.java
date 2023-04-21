package alimentari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import alimentari.entity.TipoMansione;
import alimentari.entity.Mansione;

import java.util.Optional;

public interface MansioneRepository extends JpaRepository<Mansione, Long> {
    
	Optional<Mansione> findByTipoMansione(TipoMansione tipoMansione);

}
