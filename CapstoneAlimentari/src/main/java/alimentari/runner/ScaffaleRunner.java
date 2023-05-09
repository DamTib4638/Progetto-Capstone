package alimentari.runner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import alimentari.entity.Reparto;
import alimentari.entity.Scaffale;
import alimentari.service.RepartoService;
import alimentari.service.ScaffaleService;

@Component
@Order(6)
public class ScaffaleRunner implements ApplicationRunner {
	
	@Autowired ScaffaleService scafServ;
	@Autowired RepartoService repaServ;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("Scaffale scappa e cade");
		List<Scaffale> listaScaffaliPresenti = new ArrayList<Scaffale>();
		System.out.println(listaScaffaliPresenti.size());
		listaScaffaliPresenti = scafServ.recuperaTuttiScaffali();
		System.out.println(listaScaffaliPresenti.size());
		if (listaScaffaliPresenti.size() <= 0) {
			setScaffali();
		}
//		setScaffali();
		
	}
	
	private void setScaffali() {
		Scaffale scaf;
		List<Reparto> listaReparti = repaServ.recuperaTuttiReparti();
		if (listaReparti != null) {
			for (int i=0; i<listaReparti.size(); i++) {
				for (int j=1; j<=4; j++) {
					scaf = Scaffale.builder()
							.nome("Scaffale " + j + " - Reparto " + listaReparti.get(i).getTipoReparto())
							.posizione(j)
							.numeroMaxProd(300)
							.pesoMaxProd(50.0*j)
							.reparto(listaReparti.get(i))
							.build();
					scafServ.registraScaffale(scaf);
				}
			}
		}
	}

}
