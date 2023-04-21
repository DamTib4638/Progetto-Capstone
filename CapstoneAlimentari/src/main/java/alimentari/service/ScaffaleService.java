package alimentari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.Scaffale;
import alimentari.repository.ScaffaleRepository;

@Service
public class ScaffaleService {
	
	@Autowired ScaffaleRepository scafRepo;
	
	// VALE ANCHE PER MODIFICA
	// NEL METODO CONTROLLER DOVREBBE ARRIVARTI UN ID REPARTO, QUINDI VERIFICA BENE QUANDO CREARE L'OGGETTO REPARTO A PARTIRE DALL'ID
	public Scaffale registraScaffale(Scaffale scaffale) {
		try {
			if (scaffale.getNumeroMaxProd() != null &&
				scaffale.getPesoMaxProd() != null &&
				scaffale.getPosizione() != null &&
				scaffale.getReparto() != null) {
				
				scafRepo.save(scaffale);
				System.out.println("Scaffale inserito!");
				return scaffale;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
//	public void modificaScaffale(Scaffale scaffale) {
//		try {
//			scafRepo.save(scaffale);
//			System.out.println("Scaffale modificato!");
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}
	
	public List<Scaffale> recuperaTuttiScaffali() {
		try {
			List<Scaffale> listaScaffali = scafRepo.findAll();
			return listaScaffali;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public Scaffale recuperaScaffale(Long id) {
		try {
			return scafRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
