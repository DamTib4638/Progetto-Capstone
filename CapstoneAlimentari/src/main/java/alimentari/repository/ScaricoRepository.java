package alimentari.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import alimentari.entity.Scarico;
import jakarta.persistence.OrderBy;

@Repository
public interface ScaricoRepository extends JpaRepository<Scarico, Long> {
	
	@Query("SELECT MAX(idScarico) FROM Scarico")
	Scarico findScaricoByMaxId();
	
	List<Scarico> findByDataRegScarIsNull();

}
