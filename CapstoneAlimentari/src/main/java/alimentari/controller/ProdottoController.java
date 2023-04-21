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

import alimentari.entity.Prodotto;
import alimentari.service.ProdottoService;

// oppure @CrossOrigin("http://localhost:4200")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/alimentari/prodotti")
public class ProdottoController {
	
	@Autowired ProdottoService prodServ;
	
	@PostMapping
	@PreAuthorize("hasRole('DIRETTORE')")
	public ResponseEntity<Prodotto> inserisciProdotto(@RequestBody Prodotto prodotto) {
		if(prodotto != null) {
			System.out.println(prodotto.getScaffale());
			Prodotto prodInserito = prodServ.registraProdotto(prodotto);
			return ResponseEntity.ok().body(prodInserito);
//			return new ResponseEntity<Prodotto>(prodInserito, HttpStatus.OK);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('DIRETTORE')")
	public ResponseEntity<Prodotto> modificaProdotto(@RequestBody Prodotto prodotto, @PathVariable Long id) {
		if(id>0) {
			Prodotto prodDaModificare = prodServ.recuperaProdotto(id);
			if (prodDaModificare.getIdProdotto() == prodotto.getIdProdotto()) {
				Prodotto prodModificato = prodServ.registraProdotto(prodotto);
				return ResponseEntity.ok().body(prodModificato);
			}
			
//			return new ResponseEntity<Prodotto>(prodInserito, HttpStatus.OK);
		}
		return ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Prodotto>> visualizzaTuttiProdotti() {
		return ResponseEntity.ok().body(prodServ.recuperaTuttiProdotti());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Prodotto> visualizzaProdotto(@PathVariable Long id) {
		return ResponseEntity.ok().body(prodServ.recuperaProdotto(id));
	}
	
	@GetMapping("/scaduti")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Prodotto>> visualizzaProdottiScaduti() {
		return ResponseEntity.ok().body(prodServ.recuperaProdottiScaduti());
	}
	
	@PutMapping("/scaduti/{id}")
	@PreAuthorize("hasRole('DIRETTORE')")
	public ResponseEntity<String> svuotaProdottoScaduto(@PathVariable Long id) {
		if(id > 0) {
			Boolean scartoRiuscito = prodServ.scartaProdottoScaduti(id);
			if (scartoRiuscito)
				return ResponseEntity.ok().body("Prodotto scartato correttamente!");
			else
				return ResponseEntity.badRequest().body("Errore durante lo scarto del Prodotto. Correggere!");
//			return new ResponseEntity<Prodotto>(prodInserito, HttpStatus.OK);
		}
		return ResponseEntity.badRequest().body("Il prodotto selezionato non è stato inviato allo scarto in modo corretto. Correggere!");
	}
	
	@GetMapping("/categorie/{categoria}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Prodotto>> visualizzaProdottiCategoria(@PathVariable String categoria) {
		return ResponseEntity.ok().body(prodServ.recuperaProdottiPerCategoria(categoria));
	}
	
	@GetMapping("/offerte")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<List<Prodotto>> visualizzaProdottiOfferta() {
		return ResponseEntity.ok().body(prodServ.recuperaProdottiOfferta());
	}

}
