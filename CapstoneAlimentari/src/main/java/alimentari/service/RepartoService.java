package alimentari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.Reparto;
import alimentari.repository.RepartoRepository;

@Service
public class RepartoService {
	
	@Autowired RepartoRepository repaRepo;
	
	// VALE ANCHE PER MODIFICA
	public Reparto registraReparto(Reparto reparto) {
		try {
			if(reparto.getTipoReparto() != null) {
				repaRepo.save(reparto);
				System.out.println("Reparto registrato!");
				return reparto;
			} else {
				System.err.println("Almeno un dato obbligatorio non è stato inserito. Correggere!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public List<Reparto> recuperaTuttiReparti() {
		try {
			List<Reparto> listaReparti = repaRepo.findAll();
			return listaReparti;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public Reparto recuperaReparto(Long id) {
		try {
			return repaRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
//	public List<Reparto> modificaReparto(Reparto reparto) {
//		try {
//			repaRepo.save(reparto);
//			return recuperaTuttiReparti();
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return null;
//	}

}
