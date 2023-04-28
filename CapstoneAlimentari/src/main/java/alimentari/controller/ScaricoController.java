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

import alimentari.entity.Scarico;
import alimentari.payload.OrdinazioneDTO;
import alimentari.service.ScaricoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/scarichi")
public class ScaricoController {
	
	@Autowired ScaricoService scarServ;
	
	@PostMapping
	@PreAuthorize("hasRole('DIRETTORE') || hasRole('MAGAZZINIERE_BANCO') || hasRole('MAGAZZINIERE_SCAFFALE')")
	public ResponseEntity<Scarico> inserisciPrenotazioneScarico(@RequestBody OrdinazioneDTO ordDto) {
		
		if(ordDto != null) {
			scarServ.generaNuovoScarico(ordDto);
			return ResponseEntity.ok().body(null);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PutMapping("/gestione/{id}")
	@PreAuthorize("hasRole('DIRETTORE') || hasRole('MAGAZZINIERE_BANCO') || hasRole('MAGAZZINIERE_SCAFFALE')")
	public ResponseEntity<Scarico> registraScarico(@PathVariable Long id, @RequestBody Scarico scarico) {
		if(id > 0) {
			Boolean registrazioneAvvenuta = scarServ.registraProdottiScarico(scarico.getIdScarico());
			if(registrazioneAvvenuta)
				return ResponseEntity.ok().body(scarico);
			else
				return ResponseEntity.badRequest().body(null); 
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@DeleteMapping("/gestione/{id}")
	@PreAuthorize("hasRole('DIRETTORE') || hasRole('MAGAZZINIERE_BANCO') || hasRole('MAGAZZINIERE_SCAFFALE')")
	public ResponseEntity<Boolean> eliminaScarico(@PathVariable Long id) {
		Boolean eliminazioneAvvenuta = scarServ.eliminaScarico(id);
		if(eliminazioneAvvenuta)
			return ResponseEntity.ok().body(true);
		else
			return ResponseEntity.badRequest().body(false);
	}
	
	@GetMapping("/gestione")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Scarico>> visualizzaScarichiNonRegistrati() {
		return ResponseEntity.ok().body(scarServ.recuperaScarichiDaRegistrare());
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Scarico>> visualizzaTuttiScarichi() {
		return ResponseEntity.ok().body(scarServ.recuperaTuttiScarichi());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Scarico> visualizzaScarico(@PathVariable Long id) {
		return ResponseEntity.ok().body(scarServ.recuperaScarico(id));
	}

}
