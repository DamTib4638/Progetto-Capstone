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

import alimentari.entity.Fornitore;
import alimentari.service.FornitoreService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/fornitori")
public class FornitoreController {
	
	@Autowired FornitoreService fornServ;
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Fornitore> inserisciFornitore(@RequestBody Fornitore fornitore) {
		if (fornitore != null) {
			Fornitore fornInserito = fornServ.registraFornitore(fornitore);
			return ResponseEntity.ok().body(fornInserito);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Fornitore> modificaFornitore(@PathVariable Long id) {
		if (id>0) {
			Fornitore fornitoreDaModificare = fornServ.recuperaFornitore(id);
			Fornitore fornModificato = fornServ.registraFornitore(fornitoreDaModificare);
			return ResponseEntity.ok().body(fornModificato);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Fornitore>> visualizzaTuttiFornitori() {
		return ResponseEntity.ok().body(fornServ.recuperaTuttiFornitori());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Fornitore> visualizzaFornitore(@PathVariable Long id) {
		return ResponseEntity.ok().body(fornServ.recuperaFornitore(id));
	}

}
