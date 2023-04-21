package alimentari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import alimentari.entity.Scaffale;

@Repository
public interface ScaffaleRepository extends JpaRepository<Scaffale, Long> {

}
