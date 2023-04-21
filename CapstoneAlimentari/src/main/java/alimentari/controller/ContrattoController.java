package alimentari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alimentari.entity.Contratto;
import alimentari.service.ContrattoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/contratti")
public class ContrattoController {
	
	@Autowired ContrattoService contServ;
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Contratto> inserisciContratto(@RequestBody Contratto c) {
		if(c != null) {
			Contratto contratto = contServ.registraContratto(c);
			return ResponseEntity.ok().body(contratto);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Contratto> modificaContratto(@PathVariable Long id) {
		if(id>0) {
			Contratto contrDaModificare = contServ.recuperaContratto(id);
			Contratto contrModificato = contServ.registraContratto(contrDaModificare);
			return ResponseEntity.ok().body(contrModificato);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Contratto>> visualizzaTuttiContratti() {
		return ResponseEntity.ok().body(contServ.recuperaTuttiContratti());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Contratto> visualizzaContratto(@PathVariable Long id) {
		return ResponseEntity.ok().body(contServ.recuperaContratto(id));
	}

}
