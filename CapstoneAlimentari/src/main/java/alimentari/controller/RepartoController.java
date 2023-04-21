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

import alimentari.entity.Reparto;
import alimentari.service.RepartoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/reparti")
public class RepartoController {
	
	@Autowired RepartoService repaServ;
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Reparto> inserisciReparto (@RequestBody Reparto r) {
		if (r != null) {
			Reparto reparto = repaServ.registraReparto(r);
			return ResponseEntity.ok().body(reparto);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Reparto> modificaReparto(@PathVariable Long id) {
		if (id>0) {
			Reparto repartoDaModificare = repaServ.recuperaReparto(id);
			Reparto repartoModificato = repaServ.registraReparto(repartoDaModificare);
			return ResponseEntity.ok().body(repartoModificato);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Reparto>> visualizzaTuttiReparti() {
		return ResponseEntity.ok().body(repaServ.recuperaTuttiReparti());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Reparto> visualizzaReparto(@PathVariable Long id) {
		return ResponseEntity.ok().body(repaServ.recuperaReparto(id));
	}

}
