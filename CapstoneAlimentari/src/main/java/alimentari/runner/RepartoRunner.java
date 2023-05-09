package alimentari.runner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import alimentari.entity.Reparto;
import alimentari.entity.TipoReparto;
import alimentari.service.RepartoService;

@Component
@Order(5)
public class RepartoRunner implements ApplicationRunner {
	
	@Autowired RepartoService repaServ;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("Reparto run..");
		List<Reparto> listaRepartiPresenti = new ArrayList<Reparto>();
		System.out.println(listaRepartiPresenti.size());
		listaRepartiPresenti = repaServ.recuperaTuttiReparti();
		System.out.println(listaRepartiPresenti.size());
		if (listaRepartiPresenti.size() <= 0) {
			setReparti();
		}
//		setReparti();
		
	}
	
	private void setReparti() {
		
		Reparto animali = Reparto.builder()
			.tipoReparto(TipoReparto.ANIMALI)
			.descrizione("Fanno parte del reparto ANIMALI i seguenti prodotti: Alimenti, Giochi, Pulizia e Accessori degli animali.")
			.build();
		repaServ.registraReparto(animali);
		
		Reparto bio = Reparto.builder()
			.tipoReparto(TipoReparto.BIO)
			.descrizione("Fanno parte del reparto BIO i seguenti prodotti: Alimenti per diete particolari (GLUTEN-FREE, NO LATTOSIO) ed i prodotti presenti negli altri reparti ma composti o generati da/con ingredienti biologici oppure a KM-0.")
			.build();
		repaServ.registraReparto(bio);
		
		Reparto carnePesce = Reparto.builder()
			.tipoReparto(TipoReparto.CARNE_PESCE)
			.descrizione("Fanno parte del reparto CARNE e PESCE i seguenti prodotti: Carni rosse, Carni bianche, Pesce, Frutti di mare, Salumi e Affettati.")
			.build();
		repaServ.registraReparto(carnePesce);
		
		Reparto casalinghi = Reparto.builder()
			.tipoReparto(TipoReparto.CASALINGHI)
			.descrizione("Fanno parte del reparto CASALINGHI i seguenti prodotti: Pentole, Padelle, Piatti, Bicchieri, Posate ed altri accessori. Prodotti per la cura della casa e degli elettrodomestici.")
			.build();
		repaServ.registraReparto(casalinghi);
		
		Reparto forno = Reparto.builder()
			.tipoReparto(TipoReparto.FORNO)
			.descrizione("Fanno parte del reparto FORNO i seguenti prodotti: Pane (fresco o confezionato), Torte, Pasticcini, Biscotti, Snack salati e Panini.")
			.build();
		repaServ.registraReparto(forno);
		
		Reparto fruttaVerdura = Reparto.builder()
			.tipoReparto(TipoReparto.FRUTTA_VERDURA)
			.descrizione("Fanno parte del reparto FRUTTA e VERDURA i seguenti prodotti: Frutta e Verdura (fresca o confezionata), Macedonie ed Insalate pronte.")
			.build();
		repaServ.registraReparto(fruttaVerdura);
		
		Reparto igienePersonale = Reparto.builder()
			.tipoReparto(TipoReparto.IGIENE_PERSONALE)
			.descrizione("Fanno parte del reparto IGIENE PERSONALE i seguenti prodotti: Shampoo, Saponi, Creme, Deodoranti, Spazzolini, Dentifrici, Disinfettanti, Assorbenti, Pannolini, Carta igienica, Fazzoletti e Tovaglioli.")
			.build();
		repaServ.registraReparto(igienePersonale);
		
		Reparto latteFormaggi = Reparto.builder()
			.tipoReparto(TipoReparto.LATTE_FORMAGGI)
			.descrizione("Fanno parte del reparto LATTE e FORMAGGI i seguenti prodotti: Latte, Formaggio, Yogurt, Burro e Panna.")
			.build();
		repaServ.registraReparto(latteFormaggi);
		
		Reparto surgelati = Reparto.builder()
			.tipoReparto(TipoReparto.SURGELATI)
			.descrizione("Fanno parte del reparto SURGELATI i seguenti prodotti: Gelati e dolci surgelati, Minestroni e zuppe, Verdure, Pizze e prodotti pronti surgelati, Alimenti precotti.")
			.build();
		repaServ.registraReparto(surgelati);
		
		Reparto tempoLibero = Reparto.builder()
			.tipoReparto(TipoReparto.TEMPO_LIBERO)
			.descrizione("Fanno parte del reparto TEMPO LIBERO i seguenti prodotti: Giochi, Articoli sportivi, Articoli da giardino, Articoli da bricolage, Libri, Riviste e Abbonamenti per Pay-TV.")
			.build();
		repaServ.registraReparto(tempoLibero);
		
		Reparto bevande = Reparto.builder()
			.tipoReparto(TipoReparto.BEVANDE)
			.descrizione("Fanno parte del reparto BEVANDE i seguenti prodotti: Acqua, Succhi di Frutta, The, Bibite Gassate, Vini, Birre, Liquori e, in generale, qualsiasi bevanda alcolica o analcolica.")
			.build();
		repaServ.registraReparto(bevande);
		
		Reparto bimbi = Reparto.builder()
			.tipoReparto(TipoReparto.BIMBI)
			.descrizione("Fanno parte del reparto BIMBI i seguenti prodotti: Alimenti, Abbigliamento, Giocattoli, Accessori per neonati e prima infanzia, Prodotti per l'intimo e l'igiene personale di neonati e prima infanzia.")
			.build();
		repaServ.registraReparto(bimbi);
			
		Reparto dolciumi = Reparto.builder()
			.tipoReparto(TipoReparto.DOLCIUMI)
			.descrizione("Fanno parte del reparto DOLCIUMI i seguenti prodotti: Cioccolato, Caramelle, Gomme da masticare, Dolci da forno, Biscotti, Merendine, Creme spalmabili e ingredienti per preparare dolci.")
			.build();
		repaServ.registraReparto(dolciumi);
				
		Reparto confezioni = Reparto.builder()
			.tipoReparto(TipoReparto.CONFEZIONI)
			.descrizione("Fanno parte del reparto CONFEZIONI i seguenti prodotti: Pasta, Riso, Cereali, Legumi, Sughi, Condimenti, Uova e, in generale, prodotti confezionati vendibili singolarmente.")
			.build();
		repaServ.registraReparto(confezioni);
		
	}

}
