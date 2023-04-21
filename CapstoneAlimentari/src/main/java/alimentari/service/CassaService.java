package alimentari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.Cassa;
import alimentari.repository.CassaRepository;

@Service
public class CassaService {
	
	@Autowired CassaRepository cassaRepo;
	
	// VALE ANCHE PER MODIFICA
	public Cassa registraCassa(Cassa cassa) {
		try {
			if(cassa.getIdCassa() != null) {
				if (cassa.getCassaFunzionante()) {
					cassa.setCassaFunzionante(false);
					cassaRepo.save(cassa);
					return cassa;
				} else {
					cassa.setCassaFunzionante(true);
					cassaRepo.save(cassa);
					return cassa;
				}
			} else if(cassa.getCassaFunzionante() != null && 
						cassa.getNumeroCassa() != null && !(cassaRepo.existsByNumeroCassa(cassa.getNumeroCassa()))) {
				cassaRepo.save(cassa);
				return cassa;
			} else {
				System.err.println("Almeno un dato obbligatorio non è stato inserito oppure numero cassa già esistente.Correggere!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public List<Cassa> recuperaTutteCasse() {
		try {
			List<Cassa> listaCasse = cassaRepo.findAll();
			return listaCasse;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public Cassa recuperaCassa(Long id) {
		try {
			return cassaRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// DEVI UNIRE INSERIMENTO E MODIFICA DELLA CASSA
//	public List<Cassa> modificaCassa(CassaDTO cassaDto) {
//		try {
//			Cassa cassa;
//			if(cassaDto.getId() != null) {
//				cassa = cassaRepo.findById(cassaDto.getId()).get();
//			} else {
//				cassa = new Cassa();
//			}
//			cassaRepo.save(cassa);
//			return recuperaTutteCasse();
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return null;
//	}

}
