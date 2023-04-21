package alimentari.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alimentari.entity.Contratto;
import alimentari.entity.ContrattoDipendente;

@Repository
public interface ContrattoDipendenteRepository extends JpaRepository<ContrattoDipendente, Long> {
	
	Boolean existsByContrattoAndDataFineIsNullOrDataFineIsLessThan(Contratto contratto, Date data);
	// IN ENTRAMBE LE QUERY MANCA IL CONTROLLO CON DATA-FINE è MINORE DI OGGI
	List<ContrattoDipendente> findDipendenteContrattoDipendenteContrattoByDataFineIsNullOrDataFineIsLessThan(Date data);

}
