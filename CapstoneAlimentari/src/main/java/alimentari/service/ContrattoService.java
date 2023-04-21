package alimentari.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.Contratto;
import alimentari.repository.ContrattoDipendenteRepository;
import alimentari.repository.ContrattoRepository;

@Service
public class ContrattoService {
	
	@Autowired ContrattoRepository contRepo;
	@Autowired ContrattoDipendenteRepository contDipRepo;
	
	// VALE PER INSERIMENTO E MODIFICA
	// POST E PUT
	public Contratto registraContratto(Contratto contratto) {
		try {
			if (contratto.getPagaOraria() != null &&
				contratto.getMinutiGiornalieriTurno() != null &&
				contratto.getMinutiSettimanaliTurno() != null &&
				contratto.getPercentualeMaggFestivo() != null &&
				contratto.getPagaOrariaStraordinario() != null &&
				contratto.getMinutiMalattiaAnno() != null &&
				contratto.getMinutiFerieAnno() != null &&
				contratto.getMinutiPermessoAnno() != null &&
				contratto.getTipoContratto() != null) {
				
				if (!(contRepo.existsByTipoContratto(contratto.getTipoContratto()))) {
					contRepo.save(contratto);
					System.out.println("Contratto inserito!");
					return contratto;
				} else {
					Contratto tipoContrattoTrovato = contRepo.findByTipoContratto(contratto.getTipoContratto()).get();
					Date dataOggi = new Date();
					if (contratto.getIdContratto() == tipoContrattoTrovato.getIdContratto() &&
						!(contDipRepo.existsByContrattoAndDataFineIsNullOrDataFineIsLessThan(contratto, dataOggi))) {
						contRepo.save(contratto);
						System.out.println("Contratto registrato!");
						return contratto;
					} else {
						System.err.println("Esiste già un contratto di questo tipo oppure esiste almeno un dipendente con questo tipo di contratto ancora valido. Impossibile modificare!");
					}
				}
			} else {
				System.err.println("Almeno un dato del contratto non è stato inserito oppure la tipologia del contratto esiste già. Correggere!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET
	public List<Contratto> recuperaTuttiContratti() {
		try {
			List<Contratto> listaContratti = contRepo.findAll();
			return listaContratti;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET CON ID
	public Contratto recuperaContratto(Long id) {
		try {
			return contRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public String eliminaContratto(Long id) {
		try {
			if (contRepo.existsById(id)) {
				Contratto contrDaElim = contRepo.findById(id).get();
				Date dataOggi = new Date();
				if(!(contDipRepo.existsByContrattoAndDataFineIsNullOrDataFineIsLessThan(contrDaElim, dataOggi))) {
					contRepo.delete(contrDaElim);
					return "Contratto eliminato.";
				} else {
					return "Non è possibile eliminare il contratto, perchè esiste almeno un dipendente con questo contratto ancora attivo. Correggere!";
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return "Non esiste il contratto che si vuole eliminare. Correggere!";
	}

}
