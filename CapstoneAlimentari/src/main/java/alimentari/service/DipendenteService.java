package alimentari.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.Dipendente;
import alimentari.repository.DipendenteRepository;

@Service
public class DipendenteService {
	
	@Autowired DipendenteRepository dipRepo;
	
//	public void modificaDipendente(Dipendente dipendente) {
//		try {
//			dipRepo.save(dipendente);
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}
	
	public Boolean eliminaDipendente(Long id) {
		try {
			if(dipRepo.existsById(id)) {
				dipRepo.deleteById(id);
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	public List<Dipendente> recuperaTuttiDipendenti() {
		try {
//			List<Dipendente> listaDipendenti = dipRepo.findAll();
			List<Dipendente> listaDipendenti = dipRepo.findAllByOrderByIdDipendente();
			return listaDipendenti;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public Dipendente recuperaDipendente(Long id) {
		try {
			return dipRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public Optional<Dipendente> recuperaDipendentePerEmail(String email) {
		try {
			return dipRepo.findByEmail(email);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
