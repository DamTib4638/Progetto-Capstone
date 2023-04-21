package alimentari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alimentari.entity.DettaglioTransazione;
import alimentari.entity.Transazione;

@Repository
public interface DettaglioTransazioneRepository extends JpaRepository<DettaglioTransazione, Long> {
	
	List<DettaglioTransazione> findByTransazione(Transazione transazione);

}
