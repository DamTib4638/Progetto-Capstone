package alimentari.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alimentari.entity.DettaglioScarico;
import alimentari.entity.Scarico;

@Repository
public interface DettaglioScaricoRepository extends JpaRepository<DettaglioScarico, Long> {
	
	List<DettaglioScarico> findByScarico(Scarico scarico);

}
