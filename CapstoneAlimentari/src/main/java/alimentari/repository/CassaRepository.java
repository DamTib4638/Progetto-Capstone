package alimentari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alimentari.entity.Cassa;

@Repository
public interface CassaRepository extends JpaRepository<Cassa, Long> {
	
	Boolean existsByNumeroCassa(Integer numero);

}
