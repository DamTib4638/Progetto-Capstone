package alimentari.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.CategoriaProdotto;
import alimentari.entity.Prodotto;
import alimentari.entity.TipoProdotto;
import alimentari.repository.ProdottoRepository;

@Service
public class ProdottoService {
	
	@Autowired ProdottoRepository prodRepo;
	
	// VALE ANCHE PER MODIFICA
	// NEL METODO CONTROLLER DOVREBBE ARRIVARTI UN ID SCAFFALE, QUINDI VERIFICA BENE QUANDO CREARE L'OGGETTO SCAFFALE A PARTIRE DALL'ID
	
	// POST e PUT
	public Prodotto registraProdotto(Prodotto prodotto) {
		try {
			if (prodotto.getNome() != null &&
				prodotto.getMarca() != null &&
				prodotto.getIngredienti() != null &&
				prodotto.getCategoriaProdotto() != null &&
				prodotto.getTipoProdotto() != null &&
				prodotto.getDataScadenza() != null &&
				prodotto.getPrezzoAcquistoUnitario() != null &&
				prodotto.getPrezzoVenditaUnitario() != null &&
				prodotto.getScaffale() != null) {
				
				if (prodotto.getIdProdotto() == null) {
					prodRepo.save(prodotto);
					return prodotto;
				} else {
					
					Prodotto prodottoPresente = prodRepo.findById(prodotto.getIdProdotto()).get();
					System.out.println(prodottoPresente.getNome());
					if (prodottoPresente != null) {
						if ((prodotto.getNome() != null || !(prodotto.getNome().equals(""))) && !(prodotto.getNome().equalsIgnoreCase(prodottoPresente.getNome())))
							prodottoPresente.setNome(prodotto.getNome());
						if ((prodotto.getMarca() != null || !(prodotto.getMarca().equals(""))) && !(prodotto.getMarca().equalsIgnoreCase(prodottoPresente.getMarca())))
							prodottoPresente.setMarca(prodotto.getMarca());
						
						//descrizione
						if ((prodotto.getDescrizione() != null || !(prodotto.getDescrizione().equals(""))) && !(prodotto.getDescrizione().equalsIgnoreCase(prodottoPresente.getDescrizione())))
							prodottoPresente.setDescrizione(prodotto.getDescrizione());
						//ingredienti
						if ((prodotto.getIngredienti() != null || !(prodotto.getIngredienti().equals(""))) && !(prodotto.getIngredienti().equalsIgnoreCase(prodottoPresente.getIngredienti())))
							prodottoPresente.setIngredienti(prodotto.getIngredienti());
						//tipoprodotto
						if (prodotto.getTipoProdotto() != null && prodotto.getTipoProdotto() != prodottoPresente.getTipoProdotto())
							prodottoPresente.setTipoProdotto(prodotto.getTipoProdotto());
						//categoriaprodotto
						if (prodotto.getCategoriaProdotto() != null && prodotto.getCategoriaProdotto() != prodottoPresente.getCategoriaProdotto())
							prodottoPresente.setCategoriaProdotto(prodotto.getCategoriaProdotto());
						//prezzoACQ
						if (prodotto.getPrezzoAcquistoUnitario() != null && prodotto.getPrezzoAcquistoUnitario()>0)
							prodottoPresente.setPrezzoAcquistoUnitario(prodotto.getPrezzoAcquistoUnitario());
						//prezzoVEN
						if (prodotto.getPrezzoVenditaUnitario() != null && prodotto.getPrezzoVenditaUnitario()>0)
							prodottoPresente.setPrezzoVenditaUnitario(prodotto.getPrezzoVenditaUnitario());
						//percOff
						if (prodotto.getPercentualeOfferta() != null && prodotto.getPercentualeOfferta()>0)
							prodottoPresente.setPercentualeOfferta(prodotto.getPercentualeOfferta());
						//scaffale
						if (prodotto.getScaffale() != null)
							prodottoPresente.setScaffale(prodotto.getScaffale());
						
						if (prodotto.getDataScadenza() != null)
							prodottoPresente.setDataScadenza(prodotto.getDataScadenza());
						if (prodottoPresente.getQtaDisponibile() != null && prodotto.getQtaDisponibile()>0)
							prodottoPresente.setQtaDisponibile(prodotto.getQtaDisponibile());
						if (prodottoPresente.getPesoDisponibile() != null && prodotto.getPesoDisponibile()>0)
							prodottoPresente.setPesoDisponibile(prodotto.getPesoDisponibile());
						prodRepo.save(prodottoPresente);
						return prodottoPresente;
					}
				}
			} else {
				System.err.println("Almeno un dato necessario del prodotto non è stato inserito. Correggere!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET
	public List<Prodotto> recuperaProdottiScaduti() {
		try {
			List<Prodotto> listaProdottiScaduti = prodRepo.findByQtaDisponibileOrPesoDisponibileIsGreaterThanAndDataScadenzaIsLessThan(0, 0.0, new Date());
			return listaProdottiScaduti;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// PUT (PRODOTTO IN CUI SERVE SOLO ID IN MODO NECESSARIO)
	public Boolean scartaProdottoScaduti(Long id) {
		try {
			if(prodRepo.existsById(id)) {
				Prodotto prodottoDaScartare = prodRepo.findById(id).get();
				if (prodottoDaScartare.getQtaDisponibile() != null)
					prodottoDaScartare.setQtaDisponibile(0);
				if (prodottoDaScartare.getPesoDisponibile() != null)
					prodottoDaScartare.setPesoDisponibile(0.0);
				prodRepo.save(prodottoDaScartare);
				return true;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	// GET (STRING)
	public List<Prodotto> recuperaProdottiPerCategoria(String categoria) {
		try {
			List<Prodotto> listaProdottiCategoria = prodRepo.findByCategoriaProdotto(getCategoria(categoria));
			if (listaProdottiCategoria != null)
				return listaProdottiCategoria;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET (STRING)
	public List<Prodotto> recuperaProdottiPerTipologia(String tipologia) {
		try {
			List<Prodotto> listaProdottiTipologia = prodRepo.findByTipoProdotto(getTipologiaProdotto(tipologia));
			if (listaProdottiTipologia != null)
				return listaProdottiTipologia;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET
	public List<Prodotto> recuperaProdottiOfferta() {
		try {
			List<Prodotto> listaProdottiInOfferta = prodRepo.findByPercentualeOffertaIsNotNull();
			if (listaProdottiInOfferta != null)
				return listaProdottiInOfferta;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET
	public List<Prodotto> recuperaTuttiProdotti() {
		try {
			List<Prodotto> listaProdotti = prodRepo.findAll();
			return listaProdotti;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET (ID)
	public Prodotto recuperaProdotto(Long id) {
		try {
			return prodRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// DELETE (ID)
//	public String eliminaProdotto(Long id) {
//		try {
//			if (prodRepo.existsById(id)) {
//				Prodotto prodottoDaEliminare = prodRepo.findById(id).get();
//				prodRepo.delete(prodottoDaEliminare);
//				return "Prodotto eliminato!";
////				if ((prodottoDaEliminare.getPesoDisponibile() == 0 || prodottoDaEliminare.getPesoDisponibile() == null) &&
////					(prodottoDaEliminare.getQtaDisponibile() == 0 || prodottoDaEliminare.getQtaDisponibile() == null)) {
////					prodRepo.delete(prodottoDaEliminare);
////					return "Prodotto eliminato!";
////				} else {
////					return "Non si può eliminare il prodotto perchè ci sono ancora residui in vendita.";
////				}
//			}
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return "Non esiste il prodotto che si vuole eliminare.";
//	}
	
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, noRollbackFor = Exception.class)
//	public void faiCiclo(List<DettaglioTransazione> lista) {
//		for (DettaglioTransazione dettaglioTransazione : lista) {
//			System.out.println(dettaglioTransazione.getProdotto().getNome());
//		}
//	}
	
 	private CategoriaProdotto getCategoria(String cat) {
		if(cat.equalsIgnoreCase("ANIMALI")) return CategoriaProdotto.ANIMALI;
		else if(cat.equalsIgnoreCase("BEVANDA")) return CategoriaProdotto.BEVANDA;
		else if(cat.equalsIgnoreCase("COLAZIONE")) return CategoriaProdotto.COLAZIONE;
		else if(cat.equalsIgnoreCase("CURA_CASA")) return CategoriaProdotto.CURA_CASA;
		else if(cat.equalsIgnoreCase("CURA_PERSONALE")) return CategoriaProdotto.CURA_PERSONALE;
		else if(cat.equalsIgnoreCase("FORNO_SNACK")) return CategoriaProdotto.FORNO_SNACK;
		else if(cat.equalsIgnoreCase("FRESCO")) return CategoriaProdotto.FRESCO;
		else if(cat.equalsIgnoreCase("HOBBY")) return CategoriaProdotto.HOBBY;
		else if(cat.equalsIgnoreCase("IN_SCATOLA")) return CategoriaProdotto.IN_SCATOLA;
		else if(cat.equalsIgnoreCase("SPECIAL")) return CategoriaProdotto.SPECIAL;
		else if(cat.equalsIgnoreCase("SURGELATO")) return CategoriaProdotto.SURGELATO;
		else return null;
	}
 	
 	private TipoProdotto getTipologiaProdotto(String tipo) {
 		if(tipo.equalsIgnoreCase("ACCESSORI_ANIMALI")) return TipoProdotto.ACCESSORI_ANIMALI;
 		else if(tipo.equalsIgnoreCase("ACCESSORI_BIMBO")) return TipoProdotto.ACCESSORI_BIMBO;
 		else if(tipo.equalsIgnoreCase("ACQUA")) return TipoProdotto.ACQUA;
 		else if(tipo.equalsIgnoreCase("BAGNOSCHIUMA")) return TipoProdotto.BAGNOSCHIUMA;
 		else if(tipo.equalsIgnoreCase("BEVANDA_ALCOLICA")) return TipoProdotto.BEVANDA_ALCOLICA;
 		else if(tipo.equalsIgnoreCase("BEVANDA_ANALCOLICA")) return TipoProdotto.BEVANDA_ANALCOLICA;
 		else if(tipo.equalsIgnoreCase("BURRO")) return TipoProdotto.BURRO;
 		else if(tipo.equalsIgnoreCase("CARNE")) return TipoProdotto.CARNE;
 		else if(tipo.equalsIgnoreCase("CARTA")) return TipoProdotto.CARTA;
 		else if(tipo.equalsIgnoreCase("CEREALI")) return TipoProdotto.CEREALI;
 		else if(tipo.equalsIgnoreCase("CIBO_ANIMALI")) return TipoProdotto.CIBO_ANIMALI;
 		else if(tipo.equalsIgnoreCase("CIBO_BIMBO")) return TipoProdotto.CIBO_BIMBO;
 		else if(tipo.equalsIgnoreCase("DENTIFRICIO")) return TipoProdotto.DENTIFRICIO;
 		else if(tipo.equalsIgnoreCase("FARINA")) return TipoProdotto.FARINA;
 		else if(tipo.equalsIgnoreCase("FORMAGGIO")) return TipoProdotto.FORMAGGIO;
 		else if(tipo.equalsIgnoreCase("FRUTTA")) return TipoProdotto.FRUTTA;
 		else if(tipo.equalsIgnoreCase("INTIMO_ADULTO")) return TipoProdotto.INTIMO_ADULTO;
 		else if(tipo.equalsIgnoreCase("INTIMO_BIMBO")) return TipoProdotto.INTIMO_BIMBO;
 		else if(tipo.equalsIgnoreCase("LATTE")) return TipoProdotto.LATTE;
 		else if(tipo.equalsIgnoreCase("PANE")) return TipoProdotto.PANE;
 		else if(tipo.equalsIgnoreCase("PASTA")) return TipoProdotto.PASTA;
 		else if(tipo.equalsIgnoreCase("PESCE")) return TipoProdotto.PESCE;
 		else if(tipo.equalsIgnoreCase("PIZZA")) return TipoProdotto.PIZZA;
 		else if(tipo.equalsIgnoreCase("PULIZIA_ANIMALI")) return TipoProdotto.PULIZIA_ANIMALI;
 		else if(tipo.equalsIgnoreCase("PULIZIA_CASA")) return TipoProdotto.PULIZIA_CASA;
 		else if(tipo.equalsIgnoreCase("RISO")) return TipoProdotto.RISO;
 		else if(tipo.equalsIgnoreCase("SALSA")) return TipoProdotto.SALSA;
 		else if(tipo.equalsIgnoreCase("SHAMPOO")) return TipoProdotto.SHAMPOO;
 		else if(tipo.equalsIgnoreCase("SNACK_DOLCE")) return TipoProdotto.SNACK_DOLCE;
 		else if(tipo.equalsIgnoreCase("SNACK_SALATO")) return TipoProdotto.SNACK_SALATO;
 		else if(tipo.equalsIgnoreCase("UOVA")) return TipoProdotto.UOVA;
 		else if(tipo.equalsIgnoreCase("VERDURA")) return TipoProdotto.VERDURA;
 		else return null;
 	}

}
