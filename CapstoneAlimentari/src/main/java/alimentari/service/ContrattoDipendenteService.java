package alimentari.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.ContrattoDipendente;
import alimentari.repository.ContrattoDipendenteRepository;

@Service
public class ContrattoDipendenteService {
	
	@Autowired ContrattoDipendenteRepository contDipRepo;
	
	public List<ContrattoDipendente> recuperaContrattiDaRinnovare() {
		try {
			Date dataOggi = new Date();
			List<ContrattoDipendente> listaContrattiDaRinnovare = contDipRepo.findDipendenteContrattoDipendenteContrattoByDataFineIsNullOrDataFineIsLessThan(dataOggi);
			if (listaContrattiDaRinnovare != null)
				return listaContrattiDaRinnovare;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public List<ContrattoDipendente> recuperaTuttiContrattiDipendenti() {
		try {
			List<ContrattoDipendente> listaContrattiDipendenti = contDipRepo.findAll();
			return listaContrattiDipendenti;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
