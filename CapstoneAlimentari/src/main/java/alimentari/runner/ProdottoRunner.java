package alimentari.runner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import alimentari.entity.CategoriaProdotto;
import alimentari.entity.Prodotto;

//import alimentari.entity.Cassa;
//import alimentari.entity.CategoriaProdotto;
//import alimentari.entity.DettaglioTransazione;
//import alimentari.entity.Dipendente;
import alimentari.entity.Scaffale;
import alimentari.entity.TipoProdotto;
//import alimentari.entity.Transazione;
//import alimentari.payload.OrdinazioneDTO;
//import alimentari.payload.ProdottoDTO;
//import alimentari.service.CassaService;
//import alimentari.service.DettaglioTransazioneService;
//import alimentari.service.DipendenteService;
import alimentari.service.ProdottoService;
import alimentari.service.ScaffaleService;
//import alimentari.service.TransazioneService;

@Component
@Order(7)
public class ProdottoRunner implements ApplicationRunner {
	
	@Autowired ProdottoService prodServ;

	@Autowired ScaffaleService scafServ;
//	
//	@Autowired TransazioneService tranServ;
//	
//	@Autowired DettaglioTransazioneService dettTranServ;
//	
//	@Autowired CassaService casServ;
//	
//	@Autowired DipendenteService dipServ;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("Prodotto mangiato tutto");
		List<Prodotto> listaProdottiPresenti = new ArrayList<Prodotto>();
		System.out.println(listaProdottiPresenti.size());
		listaProdottiPresenti = prodServ.recuperaTuttiProdotti();
		System.out.println(listaProdottiPresenti.size());
		if (listaProdottiPresenti.size() <= 0) {
			System.out.println("Non ci sono prodotti");
			generaProdotto();
		} else {
			System.out.println("i prodotti ci sono");
		}
//		generaProdotto();
		
//		Scaffale scaf = scafServ.recuperaScaffale(4l);
//		
//		Cassa cassa = casServ.recuperaCassa(3l);
//		
//		Dipendente dip = dipServ.recuperaDipendente(1l);
//		
//		Prodotto p1 = Prodotto.builder()
//				.categoriaProdotto(CategoriaProdotto.BEVANDA)
//				.dataScadenza(new Date())
//				.nome("Fanta")
//				.prezzoAcquistoUnitario(1.5)
//				.prezzoVenditaUnitario(3.0)
//				.qtaDisponibile(4)
//				.scaffale(scaf)
//				.build();
//		prodServ.registraProdotto(p1);
//		Prodotto prod = prodServ.recuperaProdotto(4l);
		
//		ProdottoDTO p2 = new ProdottoDTO();
//		p2.setIdProd(prod.getIdProdotto());
//		p2.setQuantita(2.0);
		
//		List<ProdottoDTO> listaProdottiAcq = new ArrayList<ProdottoDTO>();
//		listaProdottiAcq.add(p2);
		
//		OrdinazioneDTO t1 = new OrdinazioneDTO();
//				t1.setIdCassa(cassa.getIdCassa());
//				t1.setDataEmissioneTransazione(new Date());
//				t1.setIdDipendente(dip.getIdDipendente());
//				t1.setListaProdSelezionati(listaProdottiAcq);
//		tranServ.generaNuovaTransazione(t1);
				
//		tranServ.eliminaTransazione(3l);
		
//		prodServ.eliminaProdotto(5l);
				
				
				
//		Transazione cinque = tranServ.recuperaTransazione(5l);
//		List<DettaglioTransazione> lista = cinque.getDettagliTransazione();
//		prodServ.faiCiclo(lista);
		
	}
	
	private void generaProdotto() {
		
		// Scaffale Frutta
		Scaffale scafFrutta = scafServ.recuperaScaffale(23l);
		
		// Scaffale Verdura
		Scaffale scafVer = scafServ.recuperaScaffale(21l);
		
		// Scaffale Animali
		Scaffale scafAnim = scafServ.recuperaScaffale(3l);
		
		// Scaffale Bio
		Scaffale scafBio = scafServ.recuperaScaffale(5l);
				
		// Scaffale Carne
		Scaffale scafCarne = scafServ.recuperaScaffale(9l);
		
		// Scaffale Pesce
		Scaffale scafPesce = scafServ.recuperaScaffale(11l);
		
		// Scaffale Casalinghi
		Scaffale scafCasa = scafServ.recuperaScaffale(15l);
		
		// Scaffale Forno
		Scaffale scafForno = scafServ.recuperaScaffale(18l);
		
		// Scaffale Igiene Persona
		Scaffale scafIgPer = scafServ.recuperaScaffale(25l);
		
		// Scaffale Latte e Formaggi
		Scaffale scafLatForm = scafServ.recuperaScaffale(30l);
		
		// Scaffale Surgelati
		Scaffale scafSurg = scafServ.recuperaScaffale(35l);
		
		// Scaffale Tempo Libero
		Scaffale scafTemLib = scafServ.recuperaScaffale(40l);
		
		// Scaffale Bevande
		Scaffale scafBev = scafServ.recuperaScaffale(44l);
		
		// Scaffale Bimbi
		Scaffale scafBimbi = scafServ.recuperaScaffale(46l);
		
		// Scaffale Dolciumi
		Scaffale scafDolc = scafServ.recuperaScaffale(50l);
		
		// Scaffale Confezioni
		Scaffale scafConf = scafServ.recuperaScaffale(55l);
		
		// PRODOTTI FRUTTA
		Prodotto p1 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("05/05/2024"))
				.descrizione("Confezione di banane con peso netto di 1Kg, provenienti da: Panama.").ingredienti("Banana").marca("CHIQUITA")
				.nome("Banane Chiquita").prezzoAcquistoUnitario(0.99).prezzoVenditaUnitario(1.69).qtaDisponibile(10)
				.scaffale(scafFrutta).tipoProdotto(TipoProdotto.FRUTTA).build();
		prodServ.registraProdotto(p1);
		
		Prodotto p2 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("07/07/2024"))
				.descrizione("Arance sfuse a foglia verde, provenienti da: Italia").ingredienti("Arancia").marca("NAVEL")
				.nome("Arance Navel Foglia Italia").prezzoAcquistoUnitario(1.09).prezzoVenditaUnitario(1.89).pesoDisponibile(10.0)
				.scaffale(scafFrutta).tipoProdotto(TipoProdotto.FRUTTA).build();
		prodServ.registraProdotto(p2);
		
		Prodotto p3 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("07/07/2024"))
				.descrizione("Confezione di mele - tipologia Red Delicious - con peso netto di 2Kg, provenienti da: Italia").ingredienti("Mela").marca("MARLENE")
				.nome("Mele Red Delicious").prezzoAcquistoUnitario(1.09).prezzoVenditaUnitario(2.49).qtaDisponibile(20)
				.scaffale(scafFrutta).tipoProdotto(TipoProdotto.FRUTTA).build();
		prodServ.registraProdotto(p3);
		
		Prodotto p4 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("05/05/2024"))
				.descrizione("Confezione di pere - tipologia Abate - con peso netto di 500gr, provenienti da: Italia").ingredienti("Pera").marca("PRIMIA")
				.nome("Pere Abate").prezzoAcquistoUnitario(1.29).prezzoVenditaUnitario(3.19).qtaDisponibile(15)
				.scaffale(scafFrutta).tipoProdotto(TipoProdotto.FRUTTA).build();
		prodServ.registraProdotto(p4);
		
		// PRODOTTI VERDURA
		Prodotto p5 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("05/05/2024"))
				.descrizione("Confezione di patate con peso netto di 1.5Kg, provenienti da: Italia. Indicate per preparazione di gnocchi").ingredienti("Patata").marca("RIZZOLI")
				.nome("Patate Rizzoli").prezzoAcquistoUnitario(0.49).prezzoVenditaUnitario(1.29).qtaDisponibile(18)
				.scaffale(scafVer).tipoProdotto(TipoProdotto.VERDURA).build();
		prodServ.registraProdotto(p5);
		
		Prodotto p6 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("07/07/2024"))
				.descrizione("Pomodori sfusi tondi, provenienti da: Spagna").ingredienti("Pomodoro").marca("LINDO ROJO")
				.nome("Pomodoro Tondo Liscio").prezzoAcquistoUnitario(1.49).prezzoVenditaUnitario(2.84).pesoDisponibile(20.0)
				.scaffale(scafVer).tipoProdotto(TipoProdotto.VERDURA).build();
		prodServ.registraProdotto(p6);
		
		Prodotto p7 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("07/07/2024"))
				.descrizione("Melanzane sfuse tonde, provenienti da: Italia").ingredienti("Melanzana").marca("PRIMIA")
				.nome("Melanzane Tonde").prezzoAcquistoUnitario(1.09).prezzoVenditaUnitario(2.49).pesoDisponibile(15.0)
				.scaffale(scafVer).tipoProdotto(TipoProdotto.VERDURA).build();
		prodServ.registraProdotto(p7);
		
		Prodotto p8 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("05/05/2024"))
				.descrizione("Cavolo - tipologia Romanesco - venduto al pezzo, proveniente da: Italia").ingredienti("Cavolo").marca("PRIMIA")
				.nome("Cavolo Romano Italia").prezzoAcquistoUnitario(1.54).prezzoVenditaUnitario(3.39).qtaDisponibile(12)
				.scaffale(scafVer).tipoProdotto(TipoProdotto.VERDURA).build();
		prodServ.registraProdotto(p8);
		
		// PRODOTTI ANIMALI
		Prodotto p9 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.ANIMALI).dataScadenza(generaDataScadenza("05/05/2024"))
				.descrizione("Confezione di cibo per cani. Alimento umido completo per cani adulti.").ingredienti("Cereali, Verdure, Pesce Bianco 100% Naturale").marca("CESAR")
				.nome("Cesar scelta dello chef con Pesce Bianco, Verdure e Riso Integrale").prezzoAcquistoUnitario(2.99).prezzoVenditaUnitario(5.19).qtaDisponibile(30)
				.scaffale(scafAnim).tipoProdotto(TipoProdotto.CIBO_ANIMALI).build();
		prodServ.registraProdotto(p9);
		
		Prodotto p10 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.ANIMALI).dataScadenza(generaDataScadenza("07/07/2024"))
				.descrizione("Confezione di cibo per gatti. Alimento umido completo per gatti adulti.").ingredienti("Carne e derivati, Sostanze minerali, Zuccheri, Estratti di proteine vegetali.").marca("GOURMET")
				.nome("Purina Gourmet Diamant Sfilaccetti con Manzo Prelibato").prezzoAcquistoUnitario(2.99).prezzoVenditaUnitario(5.19).qtaDisponibile(30)
				.scaffale(scafAnim).tipoProdotto(TipoProdotto.CIBO_ANIMALI).build();
		prodServ.registraProdotto(p10);
		
		Prodotto p11 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.ANIMALI).dataScadenza(generaDataScadenza("31/12/2030"))
				.descrizione("Accessori per il gioco del cane.").ingredienti("-").marca("VITAKRAFT")
				.nome("Palla Riccio Vitakraft CM.10").prezzoAcquistoUnitario(0.99).prezzoVenditaUnitario(2.49).qtaDisponibile(8)
				.scaffale(scafAnim).tipoProdotto(TipoProdotto.ACCESSORI_ANIMALI).build();
		prodServ.registraProdotto(p11);
		
		Prodotto p12 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.ANIMALI).dataScadenza(generaDataScadenza("31/12/2030"))
				.descrizione("Lettiera agglomerante bianca, a granuli grandi, per una sabbiera pulita e fresca ogni giorno.").ingredienti("Bentonite bianca").marca("SANICAT")
				.nome("Sanicat Clumping White Lettiera 8L").prezzoAcquistoUnitario(2.49).prezzoVenditaUnitario(5.49).qtaDisponibile(6)
				.scaffale(scafAnim).tipoProdotto(TipoProdotto.PULIZIA_ANIMALI).build();
		prodServ.registraProdotto(p12);
		
		// PRODOTTI BIO
		Prodotto p13 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("12/12/2023"))
				.descrizione("Confezione di carote Bio con peso netto di 500gr, provenienti da: Italia.").ingredienti("Carota Bio").marca("ALMAVERDE")
				.nome("Almaverde Bio Carote Biologiche").prezzoAcquistoUnitario(1.99).prezzoVenditaUnitario(2.99).qtaDisponibile(8)
				.scaffale(scafBio).tipoProdotto(TipoProdotto.VERDURA).build();
		prodServ.registraProdotto(p13);
		
		Prodotto p14 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("11/11/2023"))
				.descrizione("Confezione di zucchine Bio con peso netto di 500gr, provenienti da: Italia.").ingredienti("Zucchina Bio").marca("ALMAVERDE")
				.nome("Almaverde Bio Zucchine Biologiche").prezzoAcquistoUnitario(2.29).prezzoVenditaUnitario(4.29).qtaDisponibile(11)
				.scaffale(scafBio).tipoProdotto(TipoProdotto.VERDURA).build();
		prodServ.registraProdotto(p14);
		
		Prodotto p15 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRUTTA_VERDURA).dataScadenza(generaDataScadenza("12/12/2023"))
				.descrizione("Confezione di fragole Bio con peso netto di 500gr, provenienti da: Italia.").ingredienti("Fragola Bio").marca("ALMAVERDE")
				.nome("Almaverde Bio Fragole Biologiche").prezzoAcquistoUnitario(1.59).prezzoVenditaUnitario(2.69).qtaDisponibile(30)
				.scaffale(scafBio).tipoProdotto(TipoProdotto.FRUTTA).build();
		prodServ.registraProdotto(p15);
		
		Prodotto p16 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FRESCO).dataScadenza(generaDataScadenza("31/05/2023"))
				.descrizione("Confezione di 4 uova Bio, grandezza mista, provenienti da: Italia").ingredienti("Uovo Bio").marca("OVOPOLLO")
				.nome("Uova Bio Ovopollo").prezzoAcquistoUnitario(1.29).prezzoVenditaUnitario(3.19).qtaDisponibile(15)
				.scaffale(scafBio).tipoProdotto(TipoProdotto.UOVA).build();
		prodServ.registraProdotto(p16);
		
		// PRODOTTI CARNE
		Prodotto p17 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CARNE_PESCE).dataScadenza(generaDataScadenza("30/06/2023"))
				.descrizione("Confezione di Prosciutto cotto 200gr, proveniente da: Italia").ingredienti("Coscia di Suino, Nitrito di sodio, Aromi naturali, Sale, Ascorbato di sodio.").marca("PRIMIA")
				.nome("Prosciutto Cotto Primia GR.200").prezzoAcquistoUnitario(0.79).prezzoVenditaUnitario(2.00).qtaDisponibile(22)
				.scaffale(scafCarne).tipoProdotto(TipoProdotto.CARNE).build();
		prodServ.registraProdotto(p17);
		
		Prodotto p18 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CARNE_PESCE).dataScadenza(generaDataScadenza("30/06/2023"))
				.descrizione("Confezione di Speck Alto Adige IGP, proveniente da: Alto Adige").ingredienti("Fruttosio, Destrosio, Spezie, Sale, Affumicato con leggio di faggio, Antiossidante E301, Conservante E250, Carne Suino").marca("MOSER")
				.nome("Moser Speck Alto Adige IGP 90 g").prezzoAcquistoUnitario(1.29).prezzoVenditaUnitario(2.39).qtaDisponibile(23)
				.scaffale(scafCarne).tipoProdotto(TipoProdotto.CARNE).build();
		prodServ.registraProdotto(p18);
		
		Prodotto p19 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CARNE_PESCE).dataScadenza(generaDataScadenza("01/05/2023"))
				.descrizione("Confezione di carne bovino adulto - tipologia Fiorentina - con peso netto di 500gr, proveniente da: Italia").ingredienti("Carne di bovino adulto, Aromi, Conservanti.").marca("CEDI")
				.nome("Fiorentina di Bovino Adulto Italia").prezzoAcquistoUnitario(9.99).prezzoVenditaUnitario(16.99).qtaDisponibile(14)
				.scaffale(scafCarne).tipoProdotto(TipoProdotto.CARNE).build();
		prodServ.registraProdotto(p19);
		
		Prodotto p20 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CARNE_PESCE).dataScadenza(generaDataScadenza("01/05/2023"))
				.descrizione("Confezione di pollo fresco classe A - tipologia sovracosce - con peso netto di 500gr, provenienti da: Italia").ingredienti("Carne di pollo alta qualità, Aromi, Conservanti.").marca("AMADORI")
				.nome("Amadori Qualità 10+ Sovracosce di Pollo 0,500 kg").prezzoAcquistoUnitario(3.29).prezzoVenditaUnitario(6.79).qtaDisponibile(15)
				.scaffale(scafCarne).tipoProdotto(TipoProdotto.CARNE).build();
		prodServ.registraProdotto(p20);
		
		// PRODOTTI PESCE
		Prodotto p21 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CARNE_PESCE).dataScadenza(generaDataScadenza("01/05/2023"))
				.descrizione("Confezione di salmone affumicato con peso netto di 100gr, provenienti da: Norvegia.").ingredienti("Salmone, Sale").marca("FJORD")
				.nome("Fjord Salmone norvegese Finemente Affumicato 0,100 kg").prezzoAcquistoUnitario(4.99).prezzoVenditaUnitario(7.69).qtaDisponibile(18)
				.scaffale(scafPesce).tipoProdotto(TipoProdotto.PESCE).build();
		prodServ.registraProdotto(p21);
		
		Prodotto p22 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CARNE_PESCE).dataScadenza(generaDataScadenza("01/05/2023"))
				.descrizione("Confezione di mazzancolle tropicali con peso netto di 100gr, provenienti da: Paesi Bassi.").ingredienti("Sale, Citrato di sodio, Aceto, Mazzancolle tropicali.").marca("PRIMIA")
				.nome("Mazzancolle Tropicali Bio cotte e sgusciate 100 g").prezzoAcquistoUnitario(2.09).prezzoVenditaUnitario(3.99).qtaDisponibile(18)
				.scaffale(scafPesce).tipoProdotto(TipoProdotto.PESCE).build();
		prodServ.registraProdotto(p22);
		
		Prodotto p23 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CARNE_PESCE).dataScadenza(generaDataScadenza("28/04/2023"))
				.descrizione("Filettame di pesce fresco - Tipologia Platessa Pleuronectes").ingredienti("Platessa").marca("CEDI")
				.nome("Filetto Platessa Large").prezzoAcquistoUnitario(16.99).prezzoVenditaUnitario(23.49).pesoDisponibile(30.0)
				.scaffale(scafPesce).tipoProdotto(TipoProdotto.PESCE).build();
		prodServ.registraProdotto(p23);
		
		Prodotto p24 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CARNE_PESCE).dataScadenza(generaDataScadenza("28/04/2023"))
				.descrizione("Filetto di Salmone con Pelle").ingredienti("Salmone").marca("CEDI")
				.nome("Filetto di Salmone con Pelle").prezzoAcquistoUnitario(18.99).prezzoVenditaUnitario(26.99).pesoDisponibile(25.0)
				.scaffale(scafPesce).tipoProdotto(TipoProdotto.PESCE).build();
		prodServ.registraProdotto(p24);
		
		// PRODOTTI CASALINGHI
		Prodotto p25 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CURA_CASA).dataScadenza(generaDataScadenza("31/12/2029"))
				.descrizione("Detersivo liquido per superfici dure lavabili").ingredienti("Benzisothiazolinone, Glutaral, Linalool, Hexyl cinnamal, Profumo, Sapone.").marca("MASTRO LINDO")
				.nome("Mastro Lindo Detersivo Pavimenti Multiuso Lavanda 950 ml").prezzoAcquistoUnitario(0.99).prezzoVenditaUnitario(1.69).qtaDisponibile(10)
				.scaffale(scafCasa).tipoProdotto(TipoProdotto.PULIZIA_CASA).build();
		prodServ.registraProdotto(p25);
		
		Prodotto p26 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CURA_CASA).dataScadenza(generaDataScadenza("31/12/2029"))
				.descrizione("Glade Automatic Spray Ricarica, Profumatore per Ambienti, Fragranza Exotic Tropical Blossoms 269ml").ingredienti("-").marca("GLADE")
				.nome("Glade Automatic Spray Ricarica - Exotic Tropical Blossoms 269ml").prezzoAcquistoUnitario(2.19).prezzoVenditaUnitario(4.69).qtaDisponibile(10)
				.scaffale(scafCasa).tipoProdotto(TipoProdotto.PULIZIA_CASA).build();
		prodServ.registraProdotto(p26);
		
		Prodotto p27 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CURA_CASA).dataScadenza(generaDataScadenza("31/12/2029"))
				.descrizione("Candeggina Profunata").ingredienti("Agenti sbiancanti a base di cloro, Profumo").marca("ACE")
				.nome("Ace Candeggina Profunata 3 L").prezzoAcquistoUnitario(0.89).prezzoVenditaUnitario(2.49).qtaDisponibile(10)
				.scaffale(scafCasa).tipoProdotto(TipoProdotto.PULIZIA_CASA).build();
		prodServ.registraProdotto(p27);
		
		Prodotto p28 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CURA_CASA).dataScadenza(generaDataScadenza("31/12/2029"))
				.descrizione("Detersivo per stoviglie").ingredienti("Policarbossilati, Sbiancanti a base di ossigeno, Fosfonati, Profumo.").marca("PRIL")
				.nome("Pril Excellence 4in1 Caps 346g 18+2 lav.").prezzoAcquistoUnitario(2.49).prezzoVenditaUnitario(4.49).qtaDisponibile(20)
				.scaffale(scafCasa).tipoProdotto(TipoProdotto.PULIZIA_CASA).build();
		prodServ.registraProdotto(p28);
		
		// PRODOTTI FORNO
		Prodotto p29 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.FORNO_SNACK).dataScadenza(generaDataScadenza("05/05/2023"))
				.descrizione("Pane Integrale di vari tipi.").ingredienti("Farina integrale di grano tenero tipo 2, Acqua, Sale, Farina di cereali maltati, Lievito di birra.").marca("FORNO")
				.nome("Pane Integrale da Banco").prezzoAcquistoUnitario(0.69).prezzoVenditaUnitario(2.69).qtaDisponibile(80)
				.scaffale(scafForno).tipoProdotto(TipoProdotto.PANE).build();
		prodServ.registraProdotto(p29);
		
		Prodotto p30 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.COLAZIONE).dataScadenza(generaDataScadenza("07/07/2023"))
				.descrizione("Muffin Triplo Cioccolato").ingredienti("Zucchero, Oli E Grassi Vegetali (Colza), Farina (Frumento), Uovo, Cacao Magro In Polvere, Acqua, Pasta Di Cacao.").marca("FORNO")
				.nome("Muffin Triplo Cioccolato").prezzoAcquistoUnitario(0.69).prezzoVenditaUnitario(1.29).qtaDisponibile(25)
				.scaffale(scafDolc).tipoProdotto(TipoProdotto.SNACK_DOLCE).build();
		prodServ.registraProdotto(p30);
		
		// PRODOTTI IN SCATOLA
		Prodotto p31 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.IN_SCATOLA).dataScadenza(generaDataScadenza("07/07/2025"))
				.descrizione("Tonno all'Olio Extravergine di Oliva").ingredienti("Tonno, Olio extravergina di oliva").marca("NOSTROMO")
				.nome("Tonno all'Olio Extravergine di Oliva 3 x 70 g").prezzoAcquistoUnitario(2.29).prezzoVenditaUnitario(4.49).qtaDisponibile(10)
				.scaffale(scafConf).tipoProdotto(TipoProdotto.PESCE).build();
		prodServ.registraProdotto(p31);
		
		Prodotto p32 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.IN_SCATOLA).dataScadenza(generaDataScadenza("05/05/2025"))
				.descrizione("Pasta di semola di grano duro").ingredienti("Semola di grano duro qualità AUREO, Acqua.").marca("VOIELLO")
				.nome("Voiello la Mezza Penna Rigata 500g").prezzoAcquistoUnitario(0.59).prezzoVenditaUnitario(1.09).qtaDisponibile(18)
				.scaffale(scafConf).tipoProdotto(TipoProdotto.PASTA).build();
		prodServ.registraProdotto(p32);
		
		// PRODOTTI IGIENE PERSONA
		Prodotto p33 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CURA_PERSONALE).dataScadenza(generaDataScadenza("05/05/2026"))
				.descrizione("Dentifricio al fluoro").ingredienti("Tetrasodium Pyrophosphate, Hydroxypropyl Methylcellulose, Hydrated Silica, Sodium Saccharin, Acqua, Sodium Fluoride").marca("COLGATE")
				.nome("Colgate Max White Crystals Dentifricio 75 ml").prezzoAcquistoUnitario(0.49).prezzoVenditaUnitario(1.19).qtaDisponibile(30)
				.scaffale(scafIgPer).tipoProdotto(TipoProdotto.INTIMO_ADULTO).build();
		prodServ.registraProdotto(p33);
		
		Prodotto p34 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.CURA_PERSONALE).dataScadenza(generaDataScadenza("05/05/2026"))
				.descrizione("Badedas Noir Classic Doccia 250 ml").ingredienti("Acqua, Acido citrico, Cloruro di sodio, Profumo, Sapone.").marca("BADEDAS")
				.nome("Badedas Noir Classic Doccia 250 ml").prezzoAcquistoUnitario(1.09).prezzoVenditaUnitario(1.89).qtaDisponibile(16)
				.scaffale(scafIgPer).tipoProdotto(TipoProdotto.INTIMO_ADULTO).build();
		prodServ.registraProdotto(p34);
		
		// PRODOTTI BEVANDE
		Prodotto p35 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.BEVANDA).dataScadenza(generaDataScadenza("07/07/2024"))
				.descrizione("Acqua minerale naturale oligominerale microbiologicamente pura").ingredienti("Acqua").marca("SAN BENEDETTO")
				.nome("Acqua Minerale San Benedetto Legg. Frizzante pet 1,5 L").prezzoAcquistoUnitario(0.49).prezzoVenditaUnitario(2.49).qtaDisponibile(20)
				.scaffale(scafBev).tipoProdotto(TipoProdotto.ACQUA).build();
		prodServ.registraProdotto(p35);
		
		Prodotto p36 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.BEVANDA).dataScadenza(generaDataScadenza("05/05/2038"))
				.descrizione("Gewürztraminer Alto Adige DOC, vino bianco italiano. Alto Adige Denominazione di Origine Controllata").ingredienti("Uva, Contiene solfiti").marca("ST MICHAEL EPPAN")
				.nome("St Michael Eppan Gewürztraminer Alto Adige DOC 750 ml").prezzoAcquistoUnitario(4.49).prezzoVenditaUnitario(9.69).qtaDisponibile(12)
				.scaffale(scafBev).tipoProdotto(TipoProdotto.BEVANDA_ALCOLICA).build();
		prodServ.registraProdotto(p36);
		
		// PRODOTTI LATTE FORMAGGI
		Prodotto p37 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.LATTE_FORMAGGIO).dataScadenza(generaDataScadenza("05/05/2023"))
				.descrizione("Latte fresco pastorizzato intero Alta Qualità").ingredienti("Latte").marca("CENTRALE DEL LATTE")
				.nome("Centrale del Latte - Latte fresco alta qualità 1L").prezzoAcquistoUnitario(0.69).prezzoVenditaUnitario(1.69).qtaDisponibile(15)
				.scaffale(scafLatForm).tipoProdotto(TipoProdotto.LATTE).build();
		prodServ.registraProdotto(p37);
		
		Prodotto p38 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.LATTE_FORMAGGIO).dataScadenza(generaDataScadenza("07/05/2023"))
				.descrizione("Granarolo Mozzarella Fresca 3 x 100 g").ingredienti("Latte, Sale, Acido citrico, Caglio microbico.").marca("GRANAROLO")
				.nome("Granarolo Mozzarella Fresca 3 x 100 g").prezzoAcquistoUnitario(1.09).prezzoVenditaUnitario(2.89).qtaDisponibile(8)
				.scaffale(scafLatForm).tipoProdotto(TipoProdotto.FORMAGGIO).build();
		prodServ.registraProdotto(p38);
		
		Prodotto p39 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.LATTE_FORMAGGIO).dataScadenza(generaDataScadenza("07/07/2023"))
				.descrizione("Prodotto caseario a pasta filata").ingredienti("Sale, Fermenti lattici, Caglio, Latte.").marca("GALBANI")
				.nome("Galbani Bel Paese Fette 120 g").prezzoAcquistoUnitario(0.79).prezzoVenditaUnitario(1.69).qtaDisponibile(20)
				.scaffale(scafLatForm).tipoProdotto(TipoProdotto.FORMAGGIO).build();
		prodServ.registraProdotto(p39);
		
		Prodotto p40 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.LATTE_FORMAGGIO).dataScadenza(generaDataScadenza("08/08/2023"))
				.descrizione("Formaggio fuso a fette con fibre").ingredienti("Sale, Formaggio, Latte scremato, Burro.").marca("SOTTILETTE")
				.nome("Sottilette Fila e Fondi con Mozzarella formaggio fuso a fette - 200 g").prezzoAcquistoUnitario(1.29).prezzoVenditaUnitario(3.19).qtaDisponibile(15)
				.scaffale(scafLatForm).tipoProdotto(TipoProdotto.FORMAGGIO).build();
		prodServ.registraProdotto(p40);
		
		// PRODOTTI SURGELATI
		Prodotto p41 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.SURGELATO).dataScadenza(generaDataScadenza("05/05/2024"))
				.descrizione("Pizza margherita con pomodorini secchi marinati, surgelata").ingredienti("Basilico, Prezzemolo, Farina di grano tenero, Mozzarella, Pomodoro, Origano, Sale, Acqua, Olio d'oliva.").marca("CAMEO")
				.nome("Ristorante Pizza Margherita Saporita 330 g").prezzoAcquistoUnitario(0.59).prezzoVenditaUnitario(1.29).qtaDisponibile(18)
				.scaffale(scafSurg).tipoProdotto(TipoProdotto.PIZZA).build();
		prodServ.registraProdotto(p41);
		
		Prodotto p42 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.SURGELATO).dataScadenza(generaDataScadenza("07/07/2024"))
				.descrizione("Biscotti al cacao magro ripieni di gelato al gusto vaniglia con biscotti al cacao magro rotti in piccoli pezzi").ingredienti("Latte, Panna, Biscotto al cacao magro, Emulsionante, Sciroppo di glucosio, Olio di cocco, Zucchero.").marca("OREO")
				.nome("OREO Sandwich con Panna x6").prezzoAcquistoUnitario(1.49).prezzoVenditaUnitario(3.89).qtaDisponibile(13)
				.scaffale(scafSurg).tipoProdotto(TipoProdotto.SNACK_DOLCE).build();
		prodServ.registraProdotto(p42);
		
		Prodotto p43 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.SURGELATO).dataScadenza(generaDataScadenza("07/07/2024"))
				.descrizione("Verdure per minestrone. Surgelate").ingredienti("Verdure miste, Patate e Legumi.").marca("FINDUS")
				.nome("Findus Minestrone Tradizione 1000 g").prezzoAcquistoUnitario(1.99).prezzoVenditaUnitario(4.99).qtaDisponibile(10)
				.scaffale(scafSurg).tipoProdotto(TipoProdotto.VERDURA).build();
		prodServ.registraProdotto(p43);
		
		Prodotto p44 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.SURGELATO).dataScadenza(generaDataScadenza("05/05/2024"))
				.descrizione("Purea di patate, aromatizzata e formata in faccine sorridenti, prefritte e surgelate").ingredienti("Patate disidratate, Amido, Sale, Patate, Spezie, Olio di girasole.").marca("MCCAIN")
				.nome("McCain Kid Smile 650 g").prezzoAcquistoUnitario(1.69).prezzoVenditaUnitario(3.24).qtaDisponibile(14)
				.scaffale(scafSurg).tipoProdotto(TipoProdotto.SNACK_SALATO).build();
		prodServ.registraProdotto(p44);
		
		// PRODOTTI TEMPO LIBERO
		Prodotto p45 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.HOBBY).dataScadenza(generaDataScadenza("31/12/2099"))
				.descrizione("Taboo - Gioco da tavolo").ingredienti("-").marca("HASBRO")
				.nome("Taboo").prezzoAcquistoUnitario(12.69).prezzoVenditaUnitario(34.49).qtaDisponibile(5)
				.scaffale(scafTemLib).tipoProdotto(TipoProdotto.TEMPO_LIBERO).build();
		prodServ.registraProdotto(p45);
		
		Prodotto p46 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.HOBBY).dataScadenza(generaDataScadenza("07/07/2034"))
				.descrizione("Selex Accendifuoco Cubetti Ecologico e Inodore 24 pezzi per Barbecue").ingredienti("-").marca("SELEX")
				.nome("Selex Accendifuoco Cubetti Ecologico e Inodore 24 pezzi").prezzoAcquistoUnitario(3.09).prezzoVenditaUnitario(6.99).qtaDisponibile(8)
				.scaffale(scafTemLib).tipoProdotto(TipoProdotto.TEMPO_LIBERO).build();
		prodServ.registraProdotto(p46);
		
		// PRODOTTI BIMBO
		Prodotto p47 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.SPECIAL).dataScadenza(generaDataScadenza("07/07/2034"))
				.descrizione("Tettina Anatomica: favorisce attivamente il corretto sviluppo della bocca. In silicone.").ingredienti("-").marca("NEO BABY")
				.nome("Baby Light Succhietto Anatomico in Silicone 0m+ Dino Blu 2 pz").prezzoAcquistoUnitario(1.09).prezzoVenditaUnitario(2.49).qtaDisponibile(10)
				.scaffale(scafBimbi).tipoProdotto(TipoProdotto.ACCESSORI_BIMBO).build();
		prodServ.registraProdotto(p47);
		
		Prodotto p48 = Prodotto.builder().categoriaProdotto(CategoriaProdotto.SPECIAL).dataScadenza(generaDataScadenza("05/05/2024"))
				.descrizione("Alimento per l'infanzia omogeneizzato pesca con mela").ingredienti("Succo di limone concentrato, Vitamina C, Frutta (Pesca 55%, Mela 44.8%)").marca("PLASMON")
				.nome("Plasmon Pesca Mela Omogeneizzato 2 x 104 g").prezzoAcquistoUnitario(1.49).prezzoVenditaUnitario(3.39).qtaDisponibile(19)
				.scaffale(scafBimbi).tipoProdotto(TipoProdotto.CIBO_BIMBO).build();
		prodServ.registraProdotto(p48);
		
	}
	
	private Date generaDataScadenza (String dataStringa) {
		SimpleDateFormat sdfIn = new SimpleDateFormat("dd/MM/yyyy");
		Date dataScadenza = null;
		try {
			dataScadenza = sdfIn.parse(dataStringa);
			return dataScadenza;
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return new Date();
	}
	
	// PER POSTICIPARE UNA DATA DI UNA SETTIMANA O DI UN MESE
	
//	public void setDataInizioAbbonamento(Date dataInizioAbbonamento) {
//        this.dataInizioAbbonamento = dataInizioAbbonamento;
//        if (this.getDurataAbbonamento() == DurataAbbonamento.Mensile) {
//            Calendar c = Calendar.getInstance();
//            c.setTime(dataInizioAbbonamento);
//            c.add(Calendar.DATE, 30);
//            this.dataFineAbbonamento = c.getTime();
//        } else if (this.getDurataAbbonamento() == DurataAbbonamento.Settimanale) {
//            Calendar c = Calendar.getInstance();
//            c.setTime(dataInizioAbbonamento);
//            c.add(Calendar.DATE, 7);
//            this.dataFineAbbonamento = c.getTime();
//        } else {
//            System.out.println("Durata non impostata");
//        }
//    }
	

}
