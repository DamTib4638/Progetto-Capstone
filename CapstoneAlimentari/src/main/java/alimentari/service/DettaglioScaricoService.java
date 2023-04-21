package alimentari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.DettaglioScarico;
import alimentari.entity.Scarico;
import alimentari.repository.DettaglioScaricoRepository;

@Service
public class DettaglioScaricoService {
	
	@Autowired DettaglioScaricoRepository dettScarRepo;
	
	public List<DettaglioScarico> recuperaTuttiDettagliScarichi() {
		try {
			List<DettaglioScarico> listaDettagliScarichi = dettScarRepo.findAll();
			return listaDettagliScarichi;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public DettaglioScarico recuperaDettaglioScarico(Long id) {
		try {
			return dettScarRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public List<DettaglioScarico> recuperaDettagliScarichiByIdScarico(Scarico scarico) {
		try {
			List<DettaglioScarico> listaDettagliScarichi = dettScarRepo.findByScarico(scarico);
			return listaDettagliScarichi;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
