package alimentari.runner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import alimentari.entity.TipoMansione;
import alimentari.entity.Dipendente;
import alimentari.entity.Mansione;
import alimentari.payload.RegisterDto;
import alimentari.repository.MansioneRepository;
import alimentari.repository.DipendenteRepository;
import alimentari.service.AuthService;


@Component
@Order(2)
public class AuthRunner implements ApplicationRunner {
	
	@Autowired MansioneRepository roleRepository;
	@Autowired DipendenteRepository userRepository;
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired AuthService authService;
	
	private Set<Mansione> ruoloDirettore;
	private Set<Mansione> ruoloCassiere;
	private Set<Mansione> ruoloMagazziniereBanco;
	private Set<Mansione> ruoloMagazziniereScaffale;
	private Set<Mansione> ruoloManager;
	private Set<Mansione> ruoloPulizia;
	private Set<Mansione> ruoloResponsabile;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("HA FATTO TUTTO...");
//		Dipendente dipCassiere2 = new Dipendente();
//		dipCassiere2.setNome("Stefano");
//		dipCassiere2.setCognome("Violi");
//		dipCassiere2.setCodFis("VLISTF0004A");
//		dipCassiere2.setEta(35);
//		dipCassiere2.setCitta("Imola");
//		dipCassiere2.setIndirizzo("Via Ferrari 35");
//		dipCassiere2.setTelefono("+393777777777");
//		dipCassiere2.setEmail("cassiere2.cassiere2@gmail.com");
//		dipCassiere2.setPassword(passwordEncoder.encode("cassiere2"));
//		userRepository.save(dipCassiere2);
//		dipCassiere2.setMansioni(ruoloCas);
		
		List<Mansione> listaMansioniPresenti = new ArrayList<Mansione>();
		List<Dipendente> listaDipendentiPresenti = new ArrayList<Dipendente>();
		System.out.println(listaMansioniPresenti.size());
		System.out.println(listaDipendentiPresenti.size());
		listaMansioniPresenti = roleRepository.findAll();
		listaDipendentiPresenti = userRepository.findAll();
		System.out.println(listaMansioniPresenti.size());
		System.out.println(listaDipendentiPresenti.size());
		if (listaDipendentiPresenti.size() <= 0 && listaMansioniPresenti.size() <= 0) {
			setRoleDefault();
			setUserDefault();
		}
		
//		setRoleDefault();
//		setUserDefault();
		
	}
	
	private void setRoleDefault() {
		Mansione direttore = new Mansione();
		direttore.setTipoMansione(TipoMansione.ROLE_DIRETTORE);
		roleRepository.save(direttore);
		
		Mansione cassiere = new Mansione();
		cassiere.setTipoMansione(TipoMansione.ROLE_CASSIERE);
		roleRepository.save(cassiere);
		
		Mansione magazziniereScaf = new Mansione();
		magazziniereScaf.setTipoMansione(TipoMansione.ROLE_MAGAZZINIERE_SCAFFALE);
		roleRepository.save(magazziniereScaf);
		
		
		
		// INSERISCI QUI LE NUOVE MANSIONI DI DEFAULT DA SALVARE NEL DB, COMMENTANDO TUTTO IL RESTO DEL METODO.
		Mansione magazziniereBanco = new Mansione();
		magazziniereBanco.setTipoMansione(TipoMansione.ROLE_MAGAZZINIERE_BANCO);
		roleRepository.save(magazziniereBanco);
		
		Mansione responsabile = new Mansione();
		responsabile.setTipoMansione(TipoMansione.ROLE_RESPONSABILE);
		roleRepository.save(responsabile);
		
		Mansione pulizia = new Mansione();
		pulizia.setTipoMansione(TipoMansione.ROLE_PULIZIA);
		roleRepository.save(pulizia);
		
		Mansione manager = new Mansione();
		manager.setTipoMansione(TipoMansione.ROLE_MANAGER);
		roleRepository.save(manager);
		
		
		ruoloDirettore = new HashSet<Mansione>();
		ruoloDirettore.add(direttore);
//		ruoloDirettore.add(cassiere);
//		ruoloDirettore.add(magazziniereScaf);
//		ruoloDirettore.add(magazziniereBanco);
//		ruoloDirettore.add(responsabile);
//		ruoloDirettore.add(manager);
		
		ruoloCassiere = new HashSet<Mansione>();
		ruoloCassiere.add(cassiere);
		
		ruoloMagazziniereBanco = new HashSet<Mansione>();
		ruoloMagazziniereBanco.add(magazziniereBanco);
		
		ruoloMagazziniereScaffale = new HashSet<Mansione>();
		ruoloMagazziniereScaffale.add(magazziniereScaf);
		
		ruoloManager = new HashSet<Mansione>();
		ruoloManager.add(manager);
		
		ruoloPulizia = new HashSet<Mansione>();
		ruoloPulizia.add(pulizia);
		
		ruoloResponsabile = new HashSet<Mansione>();
		ruoloResponsabile.add(responsabile);
		
	}
	
	private void setUserDefault() {
		

		Set<String> ruoloDir = new HashSet<>(
				ruoloDirettore.stream()
						.map(r -> r.getTipoMansione().toString())
						.collect(Collectors.toList())
				);
		Set<String> ruoloCas = new HashSet<>(
				ruoloCassiere.stream()
						.map(r -> r.getTipoMansione().toString())
						.collect(Collectors.toList())
				);
		Set<String> ruoloMagBanco = new HashSet<>(
				ruoloMagazziniereBanco.stream()
						.map(r -> r.getTipoMansione().toString())
						.collect(Collectors.toList())
				);
		Set<String> ruoloMagScaf = new HashSet<>(
				ruoloMagazziniereScaffale.stream()
						.map(r -> r.getTipoMansione().toString())
						.collect(Collectors.toList())
				);
		
		Set<String> ruoloManag = new HashSet<>(
				ruoloManager.stream()
						.map(r -> r.getTipoMansione().toString())
						.collect(Collectors.toList())
				);
		
		Set<String> ruoloPul = new HashSet<>(
				ruoloPulizia.stream()
						.map(r -> r.getTipoMansione().toString())
						.collect(Collectors.toList())
				);
		
		Set<String> ruoloResp = new HashSet<>(
				ruoloResponsabile.stream()
						.map(r -> r.getTipoMansione().toString())
						.collect(Collectors.toList())
				);
		
		
		RegisterDto dipDirettore = new RegisterDto();
		dipDirettore.setNome("Damiano");
		dipDirettore.setCognome("Tiberi");
		dipDirettore.setCodFis("TBRDMN4658A");
		dipDirettore.setEta(35);
		dipDirettore.setCitta("Bolzano");
		dipDirettore.setIndirizzo("Via Vai 5846");
		dipDirettore.setTelefono("+393333333333");
		dipDirettore.setEmail("direttore.direttore@gmail.com");
		dipDirettore.setPassword("direttore");
		dipDirettore.setMansioni(ruoloDir);
		dipDirettore.setContratto("TEMPO_INDETERMINATO");
		dipDirettore.setDataInizioContratto("2023-04-03");
		System.out.println(authService.register(dipDirettore));
		
		RegisterDto dipCassiere = new RegisterDto();
		dipCassiere.setNome("Stefano");
		dipCassiere.setCognome("Gavioli");
		dipCassiere.setCodFis("GVLSTF0001A");
		dipCassiere.setEta(23);
		dipCassiere.setCitta("Maranello");
		dipCassiere.setIndirizzo("Via Ferrari 23");
		dipCassiere.setTelefono("+393666666666");
		dipCassiere.setEmail("cassiere.cassiere@gmail.com");
		dipCassiere.setPassword("cassiere");
		dipCassiere.setMansioni(ruoloCas);
		dipCassiere.setContratto("TEMPO_INDETERMINATO");
		dipCassiere.setDataInizioContratto("2023-04-10");
		System.out.println(authService.register(dipCassiere));
		
		RegisterDto dipCassiere2 = new RegisterDto();
		dipCassiere2.setNome("Stefano");
		dipCassiere2.setCognome("Violi");
		dipCassiere2.setCodFis("VLISTF0004A");
		dipCassiere2.setEta(35);
		dipCassiere2.setCitta("Maranello");
		dipCassiere2.setIndirizzo("Via Parabolica 35");
		dipCassiere2.setTelefono("+393377777777");
		dipCassiere2.setEmail("cassiere2.cassiere2@gmail.com");
		dipCassiere2.setPassword("cassiere2");
		dipCassiere2.setMansioni(ruoloCas);
		dipCassiere2.setContratto("TEMPO_INDETERMINATO");
		dipCassiere2.setDataInizioContratto("2023-05-09");
		System.out.println(authService.register(dipCassiere2));
		
		RegisterDto dipMagazziniereBanco = new RegisterDto();
		dipMagazziniereBanco.setNome("Manuel");
		dipMagazziniereBanco.setCognome("Ferrucci");
		dipMagazziniereBanco.setCodFis("FRRMNL0002A");
		dipMagazziniereBanco.setEta(32);
		dipMagazziniereBanco.setCitta("Rocca Monfina");
		dipMagazziniereBanco.setIndirizzo("Via Ignoranza 100");
		dipMagazziniereBanco.setTelefono("+393393399339");
		dipMagazziniereBanco.setEmail("banco.banco@gmail.com");
		dipMagazziniereBanco.setPassword("banco");
		dipMagazziniereBanco.setMansioni(ruoloMagBanco);
		dipMagazziniereBanco.setContratto("TEMPO_INDETERMINATO");
		dipMagazziniereBanco.setDataInizioContratto("2023-04-15");
		System.out.println(authService.register(dipMagazziniereBanco));
		
		RegisterDto dipMagazziniereScaffale = new RegisterDto();
		dipMagazziniereScaffale.setNome("Francesco");
		dipMagazziniereScaffale.setCognome("Pastore");
		dipMagazziniereScaffale.setCodFis("PSTFRN0003A");
		dipMagazziniereScaffale.setEta(22);
		dipMagazziniereScaffale.setCitta("Genova");
		dipMagazziniereScaffale.setIndirizzo("Via dei Mille 1000");
		dipMagazziniereScaffale.setTelefono("+393401010100");
		dipMagazziniereScaffale.setEmail("scaffale.scaffale@gmail.com");
		dipMagazziniereScaffale.setPassword("scaffale");
		dipMagazziniereScaffale.setMansioni(ruoloMagScaf);
		dipMagazziniereScaffale.setContratto("TEMPO_INDETERMINATO");
		dipMagazziniereScaffale.setDataInizioContratto("2023-04-12");
		System.out.println(authService.register(dipMagazziniereScaffale));
		
	}

}
