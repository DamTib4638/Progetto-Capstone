package alimentari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alimentari.entity.Dipendente;
import alimentari.service.DipendenteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/dipendenti")
public class DipendenteController {
	
	@Autowired DipendenteService dipServ;
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('DIRETTORE')")
	public ResponseEntity<String> eliminaDipendente(@PathVariable Long id) {
		Boolean eliminazioneAvvenuta = dipServ.eliminaDipendente(id);
		if(eliminazioneAvvenuta)
			return ResponseEntity.ok().body("Dipendente eliminato correttamente!");
		else
			return ResponseEntity.badRequest().body("Errore durante la eliminazione del Dipendente. Correggere!");
	}
	
	@GetMapping
	@PreAuthorize("hasRole('DIRETTORE')")
	public ResponseEntity<List<Dipendente>> visualizzaTuttiDipendenti() {
		return ResponseEntity.ok().body(dipServ.recuperaTuttiDipendenti());
	}
	
	@GetMapping("/id/{id}")
	@PreAuthorize("hasRole('DIRETTORE')")
	public ResponseEntity<Dipendente> visualizzaDipendente(@PathVariable Long id) {
		return ResponseEntity.ok().body(dipServ.recuperaDipendente(id));
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Dipendente> visualizzaDipendentePerEmail(@PathVariable String email) {
		return ResponseEntity.ok().body(dipServ.recuperaDipendentePerEmail(email).get());
	}

}
