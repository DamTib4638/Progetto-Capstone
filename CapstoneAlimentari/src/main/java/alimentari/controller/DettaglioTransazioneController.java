package alimentari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alimentari.entity.DettaglioTransazione;
import alimentari.entity.Transazione;
import alimentari.service.DettaglioTransazioneService;
import alimentari.service.TransazioneService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/dettaglioTransazioni")
public class DettaglioTransazioneController {
	
	@Autowired DettaglioTransazioneService dettTranServ;
	@Autowired TransazioneService tranServ;
	
	@GetMapping("lista/{idTransazione}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<DettaglioTransazione>> visualizzaDettagliTransazioniPerIdTransazione(@PathVariable Long idTransazione) {
		Transazione tran = tranServ.recuperaTransazione(idTransazione);
		return ResponseEntity.ok().body(dettTranServ.recuperaDettagliTransazioniByIdTransazione(tran));
	}

}
