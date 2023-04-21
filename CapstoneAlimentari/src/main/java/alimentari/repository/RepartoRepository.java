package alimentari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alimentari.entity.Reparto;

@Repository
public interface RepartoRepository extends JpaRepository<Reparto, Long> {

}
