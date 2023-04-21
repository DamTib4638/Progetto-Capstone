package alimentari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alimentari.entity.Transazione;
import alimentari.payload.OrdinazioneDTO;
import alimentari.service.TransazioneService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/transazioni")
public class TransazioneController {
	
	@Autowired TransazioneService tranServ;
	
	@PostMapping
	@PreAuthorize("hasRole('DIRETTORE') || hasRole('CASSIERE')")
	public ResponseEntity<Transazione> inserisciEmissioneTransazione(@RequestBody OrdinazioneDTO ordDto) {
		if(ordDto != null) {
			tranServ.generaNuovaTransazione(ordDto);
			return ResponseEntity.ok().body(null);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PutMapping("/gestione/{id}")
	@PreAuthorize("hasRole('DIRETTORE') || hasRole('CASSIERE')")
	public ResponseEntity<Transazione> registraTransazione(@PathVariable Long id, @RequestBody Transazione transazione) {
		if (id>0) {
			Boolean registrazioneAvvenuta = tranServ.registraProdottiVenduti(id);
			if(registrazioneAvvenuta)
				return ResponseEntity.ok().body(transazione);
			else
				return ResponseEntity.badRequest().body(null); 
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@DeleteMapping("/gestione/{id}")
	@PreAuthorize("hasRole('DIRETTORE') || hasRole('CASSIERE')")
	public ResponseEntity<Boolean> eliminaTransazione(@PathVariable Long id) {
		Boolean eliminazioneAvvenuta = tranServ.eliminaTransazione(id);
		if(eliminazioneAvvenuta)
			return ResponseEntity.ok().body(true);
		else
			return ResponseEntity.badRequest().body(false);
	}
	
	@GetMapping("/gestione")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Transazione>> visualizzaTransazioniNonRegistrate() {
		return ResponseEntity.ok().body(tranServ.recuperaTransazioniDaRegistrare());
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Transazione>> visualizzaTutteTransazioni() {
		return ResponseEntity.ok().body(tranServ.recuperaTutteTransazioni());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Transazione> visualizzaTransazione(@PathVariable Long id) {
		return ResponseEntity.ok().body(tranServ.recuperaTransazione(id));
	}

}
