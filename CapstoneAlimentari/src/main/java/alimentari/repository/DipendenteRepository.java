package alimentari.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import alimentari.entity.Dipendente;

import java.util.List;
import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {

    Optional<Dipendente> findByEmail(String email);

//    Optional<Dipendente> findByUsernameOrEmail(String username, String email);
//
//    Optional<Dipendente> findByUsername(String username);
//
//    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    List<Dipendente> findAllByOrderByIdDipendente();
    
}
