package alimentari.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.Cassa;
import alimentari.entity.DettaglioTransazione;
import alimentari.entity.Dipendente;
import alimentari.entity.Prodotto;
import alimentari.entity.Transazione;
import alimentari.payload.OrdinazioneDTO;
import alimentari.payload.ProdottoDTO;
import alimentari.repository.CassaRepository;
import alimentari.repository.DettaglioTransazioneRepository;
import alimentari.repository.DipendenteRepository;
import alimentari.repository.ProdottoRepository;
import alimentari.repository.TransazioneRepository;

@Service
public class TransazioneService {
	
	@Autowired TransazioneRepository tranRepo;
	@Autowired DipendenteRepository dipRepo;
	@Autowired CassaRepository cassaRepo;
	@Autowired ProdottoRepository prodRepo;
	@Autowired DettaglioTransazioneRepository dettTranRepo;
	
	// POST SIA SU TRANSAZIONE CHE SU DETTAGLIO TRANSAZIONE
	public void generaNuovaTransazione(OrdinazioneDTO ordinazioneDto) {
		try {
			if (ordinazioneDto.getIdCassa() != null &&
				ordinazioneDto.getIdDipendente() != null &&
				ordinazioneDto.getListaProdotti() != null) {
				
				Cassa cassaSelezionata = cassaRepo.findById(ordinazioneDto.getIdCassa()).get();
				Dipendente dipSelezionato = dipRepo.findById(ordinazioneDto.getIdDipendente()).get();
				Transazione nuovaTransazione = Transazione.builder()
						.dataEmissioneTransazione(new Date())
						.dipendente(dipSelezionato)
						.cassa(cassaSelezionata).build();
				tranRepo.save(nuovaTransazione);
				Long ultimoId = 0l;
				List<Transazione> listaTransazioni = tranRepo.findAll();
				for (Transazione transazione : listaTransazioni) {
					ultimoId = transazione.getIdTransazione();
				}
//				Long idUltimaTransazione = tranRepo.findTransazioneMaxId();
				Transazione ultimaTran = tranRepo.findById(ultimoId).get();
				for (ProdottoDTO p : ordinazioneDto.getListaProdotti()) {
					Prodotto prodSelezionato = prodRepo.findById(p.getIdProdotto()).get();
					DettaglioTransazione nuovoDettaglioTransazione = new DettaglioTransazione();
					nuovoDettaglioTransazione.setTransazione(ultimaTran);
					nuovoDettaglioTransazione.setProdotto(prodSelezionato);
					nuovoDettaglioTransazione.setPrezzoVendita(prodSelezionato.getPrezzoVenditaUnitario()*p.getQuantita());
					if (prodSelezionato.getPesoDisponibile() != null) {
						nuovoDettaglioTransazione.setPesoVendita(p.getQuantita());
						dettTranRepo.save(nuovoDettaglioTransazione);
						System.out.println("Dettaglio Transazione inserito!");
					} else if (prodSelezionato.getQtaDisponibile() != null) {
						nuovoDettaglioTransazione.setQtaVendita(p.getQuantita().intValue());
						dettTranRepo.save(nuovoDettaglioTransazione);
						System.out.println("Dettaglio Transazione inserito!");
					} else {
						System.out.println("Questo prodotto ha sia peso che quantità nulli. Correggere!");
					}
				}
			} else {
				System.err.println("Almeno un dato obbligatorio non è stato inserito. Correggere!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	// PUT SU TRANSAZIONE E SU PRODOTTO
	public Boolean registraProdottiVenduti(Long id) {
		try {
			ProdottoService ps = new ProdottoService();
			Transazione transazione = tranRepo.findById(id).get();
			transazione.setDataPagamentoTransazione(new Date());
			tranRepo.save(transazione);
			List<DettaglioTransazione> listaDettagliTransazione = dettTranRepo.findByTransazione(transazione);
			for (DettaglioTransazione dt : listaDettagliTransazione) {
				Prodotto p = prodRepo.findById(dt.getProdotto().getIdProdotto()).get();
				if (dt.getQtaVendita() != null) {
					if(dt.getQtaVendita()<=p.getQtaDisponibile()) {
						p.setQtaDisponibile(p.getQtaDisponibile() - dt.getQtaVendita());
						prodRepo.save(p);
					} else {
						System.err.println("Stai comprando più di quanto è disponibile per questo prodotto. Correggere!");
						return false;
					}
				} else if (dt.getPesoVendita() != null) {
					if(dt.getPesoVendita()<=p.getPesoDisponibile()) {
						p.setPesoDisponibile(p.getPesoDisponibile() - dt.getPesoVendita());
						prodRepo.save(p);
					} else {
						System.err.println("Stai comprando più di quanto è disponibile per questo prodotto. Correggere!");
						return false;
					}
				} else {
					System.out.println("Problemi con quantità o peso. Correggere!");
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}
	
	// GET
	public List<Transazione> recuperaTutteTransazioni() {
		try {
//			List<Transazione> listaTransazioni = tranRepo.findAll();
			List<Transazione> listaTransazioni = tranRepo.findAllByOrderByIdTransazione();
			return listaTransazioni;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET
	public List<Transazione> recuperaTransazioniDaRegistrare() {
		try {
			List<Transazione> listaDaRegistrare = tranRepo.findByDataPagamentoTransazioneIsNull();
			return listaDaRegistrare;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET CON ID
 	public Transazione recuperaTransazione(Long id) {
		try {
			return tranRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// DELETE CON ID
	public Boolean eliminaTransazione(Long id) {
		try {
			if (tranRepo.existsById(id)) {
				Transazione transazioneDaEliminare = tranRepo.findById(id).get();
				if (transazioneDaEliminare.getDataPagamentoTransazione() == null) {
					List<DettaglioTransazione> listaDettagliTransazione = dettTranRepo.findByTransazione(transazioneDaEliminare);
					for (DettaglioTransazione dettaglioTransazione : listaDettagliTransazione) {
						dettTranRepo.delete(dettaglioTransazione);
					}
					tranRepo.delete(transazioneDaEliminare);
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

}
