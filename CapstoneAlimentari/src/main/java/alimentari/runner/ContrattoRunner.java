package alimentari.runner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import alimentari.entity.Contratto;
import alimentari.entity.TipoContratto;
import alimentari.service.ContrattoService;

@Component
@Order(1)
public class ContrattoRunner implements ApplicationRunner {
	
	@Autowired ContrattoService contServ;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("Contratto fugge da me");
		List<Contratto> listaContrattiPresenti = new ArrayList<Contratto>();
		System.out.println(listaContrattiPresenti.size());
		listaContrattiPresenti = contServ.recuperaTuttiContratti();
		System.out.println(listaContrattiPresenti.size());
		if (listaContrattiPresenti.size() <= 0) {
			setContratti();
		}
//		setContratti();
		
	}
	
	private void setContratti() {
		Contratto tempoInd = Contratto.builder()
								.minutiFerieAnno(8000)
								.minutiGiornalieriTurno(400)
								.minutiMalattiaAnno(48000)
								.minutiPermessoAnno(8000)
								.minutiSettimanaliTurno(2000)
								.pagaOraria(11.5)
								.pagaOrariaStraordinario(13.0)
								.percentualeMaggFestivo(20.0)
								.tipoContratto(TipoContratto.TEMPO_INDETERMINATO)
								.build();
		contServ.registraContratto(tempoInd);
		Contratto apprendistato = Contratto.builder()
								.minutiFerieAnno(8000)
								.minutiGiornalieriTurno(400)
								.minutiMalattiaAnno(48000)
								.minutiPermessoAnno(8000)
								.minutiSettimanaliTurno(2000)
								.pagaOraria(9.0)
								.pagaOrariaStraordinario(10.5)
								.percentualeMaggFestivo(10.0)
								.tipoContratto(TipoContratto.APPRENDISTATO)
								.build();
		contServ.registraContratto(apprendistato);
		Contratto tempoDet = Contratto.builder()
								.minutiFerieAnno(8000)
								.minutiGiornalieriTurno(400)
								.minutiMalattiaAnno(48000)
								.minutiPermessoAnno(8000)
								.minutiSettimanaliTurno(2000)
								.pagaOraria(13.0)
								.pagaOrariaStraordinario(14.5)
								.percentualeMaggFestivo(20.0)
								.tipoContratto(TipoContratto.TEMPO_DETERMINATO)
								.build();
		contServ.registraContratto(tempoDet);
		Contratto stage = Contratto.builder()
								.minutiFerieAnno(4800)
								.minutiGiornalieriTurno(240)
								.minutiMalattiaAnno(28800)
								.minutiPermessoAnno(4800)
								.minutiSettimanaliTurno(1200)
								.pagaOraria(7.5)
								.pagaOrariaStraordinario(9.0)
								.percentualeMaggFestivo(10.0)
								.tipoContratto(TipoContratto.STAGE)
								.build();
		contServ.registraContratto(stage);
	}

}
