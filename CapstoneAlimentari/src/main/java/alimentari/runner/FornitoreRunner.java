package alimentari.runner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import alimentari.entity.Fornitore;
import alimentari.service.FornitoreService;

@Component
@Order(4)
public class FornitoreRunner implements ApplicationRunner {
	
	@Autowired FornitoreService fornServ;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("fornitore corre su gomma");
		List<Fornitore> listaFornitoriPresenti = new ArrayList<Fornitore>();
		System.out.println(listaFornitoriPresenti.size());
		listaFornitoriPresenti = fornServ.recuperaTuttiFornitori();
		System.out.println(listaFornitoriPresenti.size());
		if (listaFornitoriPresenti.size() <= 0) {
			setFornitori();
		}
//		setFornitori();
		
	}
	
	private void setFornitori() {
		Fornitore f1 = Fornitore.builder()
								.citta("Roma")
								.email("fornitore1@rm.it")
								.indirizzo("Via Roma 1")
								.nomeDittaFornitore("BRT")
								.telefono("06060606")
								.dataInizioRapporto(new Date(2023-04-03))
								.build();
		fornServ.registraFornitore(f1);
		
		Fornitore f2 = Fornitore.builder()
								.citta("Bolzano")
								.email("fornitore2@bz.it")
								.indirizzo("Via Bolzano 2")
								.nomeDittaFornitore("GLS")
								.telefono("04710471")
								.dataInizioRapporto(new Date(2023-04-03))
								.build();
		fornServ.registraFornitore(f2);
				
		Fornitore f3 = Fornitore.builder()
								.citta("Trento")
								.email("fornitore3@tn.it")
								.indirizzo("Via Trento 3")
								.nomeDittaFornitore("SDA")
								.telefono("04720472")
								.dataInizioRapporto(new Date(2023-04-03))
								.build();
		fornServ.registraFornitore(f3);
	}

}
