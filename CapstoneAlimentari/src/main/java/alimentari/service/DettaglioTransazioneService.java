package alimentari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.DettaglioTransazione;
import alimentari.entity.Transazione;
import alimentari.repository.DettaglioTransazioneRepository;

@Service
public class DettaglioTransazioneService {
	
	@Autowired DettaglioTransazioneRepository dettTranRepo;
	
	public List<DettaglioTransazione> recuperaTuttiDettagliTransazioni() {
		try {
			List<DettaglioTransazione> listaDettagliTransazioni = dettTranRepo.findAll();
			return listaDettagliTransazioni;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public DettaglioTransazione recuperaDettaglioTransazione(Long id) {
		try {
			return dettTranRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public List<DettaglioTransazione> recuperaDettagliTransazioniByIdTransazione(Transazione transazione) {
		try {
			List<DettaglioTransazione> listaDettagliTransazioni = dettTranRepo.findByTransazione(transazione);
			return listaDettagliTransazioni;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
