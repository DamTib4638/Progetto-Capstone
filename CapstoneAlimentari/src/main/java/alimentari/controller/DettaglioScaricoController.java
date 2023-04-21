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

import alimentari.entity.DettaglioScarico;
import alimentari.entity.Scarico;
import alimentari.service.DettaglioScaricoService;
import alimentari.service.ScaricoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/dettaglioScarichi")
public class DettaglioScaricoController {
	
	@Autowired DettaglioScaricoService dettScarServ;
	@Autowired ScaricoService scarServ;
	
	@GetMapping("lista/{idScarico}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<DettaglioScarico>> visualizzaDettagliScarichiPerIdScarico(@PathVariable Long idScarico) {
		Scarico scar = scarServ.recuperaScarico(idScarico);
		return ResponseEntity.ok().body(dettScarServ.recuperaDettagliScarichiByIdScarico(scar));
	}

}
