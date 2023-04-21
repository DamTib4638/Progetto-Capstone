package alimentari.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.Fornitore;
import alimentari.repository.FornitoreRepository;

@Service
public class FornitoreService {
	
	@Autowired FornitoreRepository fornRepo;
	
	// VALE ANCHE PER MODIFICA
	// POST E PUT
	public Fornitore registraFornitore(Fornitore fornitore) {
		try {
			if (fornitore.getNomeDittaFornitore()!=null && 
				fornitore.getDataInizioRapporto()!=null && 
				fornitore.getTelefono()!=null && fornitore.getEmail()!=null) {
			
				if(!(fornRepo.existsByEmail(fornitore.getEmail()))) {
					fornRepo.save(fornitore);
					System.out.println("Fornitore inserito!");
					return fornitore;
				} else {
					Fornitore fornEmail = fornRepo.findByEmail(fornitore.getEmail()).get();
					if(fornitore.getIdFornitore() != null && fornitore.getIdFornitore() == fornEmail.getIdFornitore()) {
						fornRepo.save(fornitore);
						System.out.println("Fornitore inserito!");
						return fornitore;
					} else {
						System.err.println("Esiste già un altro, diverso, fornitore registrato con questa email. Correggere!");
					}
				}
				
				// UTILE PER PARSARE LE DATE
//				String dataInRappStr = fornitoreDto.getDataInizioRapporto();
//				SimpleDateFormat sdfIn = new SimpleDateFormat("dd/MM/yyyy");
//				Date dataInizioRapporto = null;
//				try {
//					dataInizioRapporto = sdfIn.parse(dataInRappStr);
//					fornitore.setDataInizioRapporto(dataInizioRapporto);
//				} catch (Exception e) {
//					System.err.println(e.getMessage());
//				}
				
			} else {
				System.err.println("Almeno un dato obbligatorio non è stato inserito.");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
//	public void modificaFornitore(Fornitore fornitore) {
//		try {
//			fornRepo.save(fornitore);
//			System.out.println("Fornitore modificato!");
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//	}
	
	// GET CON ID
	public Fornitore recuperaFornitore(Long id) {
		try {
			return fornRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET
	public List<Fornitore> recuperaTuttiFornitori() {
		try {
			List<Fornitore> listaFornitori = fornRepo.findAll();
			return listaFornitori;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

}
