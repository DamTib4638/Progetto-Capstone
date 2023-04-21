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

import alimentari.entity.Cassa;
import alimentari.service.CassaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/casse")
public class CassaController {
	
	@Autowired CassaService cassaServ;
	
	@PostMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Cassa> inserisciCassa(@RequestBody Cassa cassa) {
		if(cassa != null) {
			Cassa casInserita = cassaServ.registraCassa(cassa);
			return ResponseEntity.ok().body(casInserita);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Cassa> modificaCassa(@PathVariable Long id) {
		if(id>0) {
			Cassa cassaDaModificare = cassaServ.recuperaCassa(id);
			Cassa cassaModificata = cassaServ.registraCassa(cassaDaModificare);
			return ResponseEntity.ok().body(cassaModificata);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Cassa>> visualizzaTutteCasse() {
		return ResponseEntity.ok().body(cassaServ.recuperaTutteCasse());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Cassa> visualizzaCassa(@PathVariable Long id) {
		return ResponseEntity.ok().body(cassaServ.recuperaCassa(id));
	}

}
