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

import alimentari.entity.Scaffale;
import alimentari.service.ScaffaleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/scaffali")
public class ScaffaleController {
	
	@Autowired ScaffaleService scafServ;
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Scaffale> inserisciScaffale(@RequestBody Scaffale scaf) {
		if(scaf != null) {
			Scaffale scaffale = scafServ.registraScaffale(scaf);
			return ResponseEntity.ok().body(scaffale);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Scaffale> modificaScaffale(@PathVariable Long id) {
		if(id>0) {
			Scaffale scaffaleDaModificare = scafServ.recuperaScaffale(id);
			Scaffale scaffaleModificato = scafServ.registraScaffale(scaffaleDaModificare);
			return ResponseEntity.ok().body(scaffaleModificato);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping
//	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Scaffale>> visualizzaTuttiScaffali() {
		return ResponseEntity.ok().body(scafServ.recuperaTuttiScaffali());
	}
	
	@GetMapping("/{id}")
//	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Scaffale> visualizzaScaffale(@PathVariable Long id) {
		return ResponseEntity.ok().body(scafServ.recuperaScaffale(id));
	}

}
