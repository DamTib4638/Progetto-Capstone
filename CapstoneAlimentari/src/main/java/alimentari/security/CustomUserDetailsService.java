package alimentari.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import alimentari.entity.Dipendente;
import alimentari.repository.DipendenteRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private DipendenteRepository dipendenteRepository;

    public CustomUserDetailsService(DipendenteRepository dipRepository) {
        this.dipendenteRepository = dipRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
          Dipendente dipendente = dipendenteRepository.findByEmail(email)
                 .orElseThrow(() ->
                         new UsernameNotFoundException("User not found with email: "+ email));

        Set<GrantedAuthority> authorities = dipendente
                .getMansioni()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getTipoMansione().toString())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(dipendente.getEmail(),
        		dipendente.getPassword(),
                authorities);
    }
}
