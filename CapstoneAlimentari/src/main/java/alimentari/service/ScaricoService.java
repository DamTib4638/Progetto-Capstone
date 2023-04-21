package alimentari.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alimentari.entity.DettaglioScarico;
import alimentari.entity.Dipendente;
import alimentari.entity.Fornitore;
import alimentari.entity.Prodotto;
import alimentari.entity.Scarico;
import alimentari.payload.OrdinazioneDTO;
import alimentari.payload.ProdottoDTO;
import alimentari.repository.DettaglioScaricoRepository;
import alimentari.repository.DipendenteRepository;
import alimentari.repository.FornitoreRepository;
import alimentari.repository.ProdottoRepository;
import alimentari.repository.ScaricoRepository;

@Service
public class ScaricoService {
	
	@Autowired ScaricoRepository scarRepo;
	@Autowired DipendenteRepository dipRepo;
	@Autowired FornitoreRepository fornRepo;
	@Autowired ProdottoRepository prodRepo;
	@Autowired DettaglioScaricoRepository dettScarRepo;
	
	// POST SIA SU SCARICO CHE SU DETTAGLIO SCARICO
	public void generaNuovoScarico(OrdinazioneDTO ordinazioneDto) {
		for (ProdottoDTO p : ordinazioneDto.getListaProdotti()) {
			System.out.println(p.getIdProdotto());
			System.out.println(p.getQuantita());
		}
		try {
			if (ordinazioneDto.getIdFornitore() != null &&
				ordinazioneDto.getIdDipendente() != null &&
				ordinazioneDto.getListaProdotti() != null) {
				
				Fornitore fornSelezionato = fornRepo.findById(ordinazioneDto.getIdFornitore()).get();
				Dipendente dipSelezionato = dipRepo.findById(ordinazioneDto.getIdDipendente()).get();
				Scarico nuovoScarico = Scarico.builder()
											.dataPrenScar(new Date())
											.dipendente(dipSelezionato)
											.fornitore(fornSelezionato).build();
				scarRepo.save(nuovoScarico);
				Long ultimoId = 0l;
				List<Scarico> listaScarichi = scarRepo.findAll();
				System.out.println(listaScarichi.size());
				for (Scarico scarico : listaScarichi) {
					ultimoId = scarico.getIdScarico();
				}
				System.out.println(ultimoId);
				Scarico ultimoScarico = scarRepo.findById(ultimoId).get();
				System.out.println(ultimoScarico.getIdScarico());
//				List<Prodotto> listaProdottiSelezionati;
				for (ProdottoDTO p : ordinazioneDto.getListaProdotti()) {
					Prodotto prodSelezionato = prodRepo.findById(p.getIdProdotto()).get();
					DettaglioScarico nuovoDettaglioScarico = new DettaglioScarico();
					nuovoDettaglioScarico.setScarico(ultimoScarico);
					nuovoDettaglioScarico.setProdotto(prodSelezionato);
					nuovoDettaglioScarico.setPrezzoAcquisto(prodSelezionato.getPrezzoAcquistoUnitario()*p.getQuantita());
					if (prodSelezionato.getPesoDisponibile() != null) {
						nuovoDettaglioScarico.setPesoAcquisto(p.getQuantita());
						dettScarRepo.save(nuovoDettaglioScarico);
						System.out.println("Dettaglio scarico inserito!");
					} else if (prodSelezionato.getQtaDisponibile() != null) {
						nuovoDettaglioScarico.setQtaAcquisto(p.getQuantita().intValue());
						dettScarRepo.save(nuovoDettaglioScarico);
						System.out.println("Dettaglio scarico inserito!");
					} else {
						System.err.println("Questo prodotto ha sia peso che quantità nulli. Correggere!");
					}
				}
			} else {
				System.err.println("Almeno un dato obbligatorio non è stato inserito. Correggere!");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	// PUT SU SCARICO E SU PRODOTTO
	public Boolean registraProdottiScarico(Long id) {
		try {
			System.out.println("Id Scarico riga 89 in scarico service " + id);
			ProdottoService ps = new ProdottoService();
			Scarico scaricoDaRegistrare = scarRepo.findById(id).get();
			scaricoDaRegistrare.setDataRegScar(new Date());
			scarRepo.save(scaricoDaRegistrare);
			List<DettaglioScarico> listaDettagliScarico = dettScarRepo.findByScarico(scaricoDaRegistrare);
			for (DettaglioScarico dettaglioScarico : listaDettagliScarico) {
				System.out.println("Id Prodotto del dettaglio scarico " + dettaglioScarico.getProdotto().getIdProdotto());
			}
			for (DettaglioScarico ds : listaDettagliScarico) {
				Prodotto p = prodRepo.findById(ds.getProdotto().getIdProdotto()).get();
				Calendar c = Calendar.getInstance();
	            c.setTime(p.getDataScadenza());
	            c.add(Calendar.DATE, 30);
	            p.setDataScadenza(c.getTime());
				if (ds.getQtaAcquisto() != null) {
					p.setQtaDisponibile(p.getQtaDisponibile() + ds.getQtaAcquisto());
					prodRepo.save(p);
//					ps.registraProdotto(p);
				} else if (ds.getPesoAcquisto() != null) {
					p.setPesoDisponibile(p.getPesoDisponibile() + ds.getPesoAcquisto());
					prodRepo.save(p);
//					ps.registraProdotto(p);
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
	public List<Scarico> recuperaScarichiDaRegistrare() {
		try {
			List<Scarico> listaDaRegistrare = scarRepo.findByDataRegScarIsNull();
			return listaDaRegistrare;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET
	public List<Scarico> recuperaTuttiScarichi() {
		try {
			List<Scarico> listaScarichi = scarRepo.findAll();
			return listaScarichi;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// GET CON ID
	public Scarico recuperaScarico (Long id) {
		try {
			return scarRepo.findById(id).get();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	// DELETE CON ID
	public Boolean eliminaScarico(Long id) {
		try {
			if (scarRepo.existsById(id)) {
				Scarico scaricoDaEliminare = scarRepo.findById(id).get();
				if (scaricoDaEliminare.getDataRegScar() == null) {
					List<DettaglioScarico> listaDettagliScarico = dettScarRepo.findByScarico(scaricoDaEliminare);
					for (DettaglioScarico dettaglioScarico : listaDettagliScarico) {
						dettScarRepo.delete(dettaglioScarico);
					}
					scarRepo.delete(scaricoDaEliminare);
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
