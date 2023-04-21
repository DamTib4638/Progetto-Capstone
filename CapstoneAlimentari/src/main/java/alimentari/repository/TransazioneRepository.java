package alimentari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import alimentari.entity.Transazione;

@Repository
public interface TransazioneRepository extends JpaRepository<Transazione, Long> {
	
	@Query("SELECT MAX(idTransazione) FROM Transazione")
	Long findTransazioneMaxId();
	
	List<Transazione> findByDataPagamentoTransazioneIsNull();

}
