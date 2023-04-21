package alimentari.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alimentari.entity.Contratto;
import alimentari.entity.TipoContratto;

@Repository
public interface ContrattoRepository extends JpaRepository<Contratto, Long> {
	
	Optional<Contratto> findByTipoContratto(TipoContratto tipoContratto);
	
	Boolean existsByTipoContratto(TipoContratto tipoContratto);

}
