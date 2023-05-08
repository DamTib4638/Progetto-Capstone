package alimentari.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import alimentari.entity.TipoMansione;
import alimentari.entity.Mansione;
import alimentari.entity.TipoContratto;
import alimentari.entity.Contratto;
import alimentari.entity.ContrattoDipendente;
import alimentari.entity.Dipendente;
import alimentari.exception.MyAPIException;
import alimentari.payload.LoginDto;
import alimentari.payload.RegisterDto;
import alimentari.repository.MansioneRepository;
import alimentari.repository.ContrattoDipendenteRepository;
import alimentari.repository.ContrattoRepository;
import alimentari.repository.DipendenteRepository;
import alimentari.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private DipendenteRepository dipendenteRepository;
    private MansioneRepository mansioneRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired private ContrattoRepository contRepo;
    @Autowired private ContrattoDipendenteRepository contrDipRepo;
    private JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           DipendenteRepository dipendenteRepository,
                           MansioneRepository mansioneRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.dipendenteRepository = dipendenteRepository;
        this.mansioneRepository = mansioneRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
    	System.out.println(loginDto.getEmail());
        System.out.println(loginDto.getPassword());
    	Authentication authentication = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(
        				loginDto.getEmail(), loginDto.getPassword()
        		)
        ); 
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

//        // add check for username exists in database
//        if(dipendenteRepository.existsByUsername(registerDto.getUsername())){
//            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
//        }

        // add check for email exists in database
    	// DEVO INSERIRE IL CONTROLLO DI DIRETTORE UNICO NEL SUPERMERCATO OPPURE NON LO METTO TRA LE OPZIONI PER ORA
        if(dipendenteRepository.existsByEmail(registerDto.getEmail())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email già registrata nel DB!!!");
        }

        Dipendente dipendente = new Dipendente();
        dipendente.setNome(registerDto.getNome());
        dipendente.setCognome(registerDto.getCognome());
        dipendente.setEta(registerDto.getEta());
        dipendente.setCodFis(registerDto.getCodFis());
        dipendente.setTelefono(registerDto.getTelefono());
        dipendente.setIndirizzo(registerDto.getIndirizzo());
        dipendente.setCitta(registerDto.getCitta());
        dipendente.setEmail(registerDto.getEmail());
        dipendente.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Mansione> mansioni = new HashSet<>();
        
        if(registerDto.getMansioni() != null) {
	        registerDto.getMansioni().forEach(mansione -> {
	        	Mansione mansioneDip = mansioneRepository.findByTipoMansione(getMansione(mansione)).get();
	        	mansioni.add(mansioneDip);
	        });
        } else {
        	Mansione mansioneDipPul = mansioneRepository.findByTipoMansione(TipoMansione.ROLE_PULIZIA).get();
        	mansioni.add(mansioneDipPul);
        }
        
        dipendente.setMansioni(mansioni);
        Contratto contrAssegnato = contRepo.findByTipoContratto(getTipologiaContratto(registerDto.getContratto())).get();
        ContrattoDipendente contrattoNuovoDip = new ContrattoDipendente();
        contrattoNuovoDip.setContratto(contrAssegnato);
        contrattoNuovoDip.setDipendente(dipendente);
        String dataIninizioContrattoStringa = registerDto.getDataInizioContratto();
		SimpleDateFormat sdfInizio = new SimpleDateFormat("yyyy-MM-dd");
		Date dataInizio = null;
		try {
			dataInizio = sdfInizio.parse(dataIninizioContrattoStringa);
			contrattoNuovoDip.setDataInizio(dataInizio);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		if (!(contrAssegnato.getTipoContratto().equals(TipoContratto.TEMPO_INDETERMINATO))) {
			String dataFineContrattoStringa = registerDto.getDataFineContratto();
			SimpleDateFormat sdfFine = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFine = null;
			try {
				dataFine = sdfFine.parse(dataFineContrattoStringa);
				contrattoNuovoDip.setDataFine(dataFine);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
        System.out.println(dipendente);
        dipendenteRepository.save(dipendente);
        contrDipRepo.save(contrattoNuovoDip);

        return "Dipendente registrato correttamente!!!";
    }
    
    private TipoMansione getMansione(String mans) {
    	if(mans.equals("ROLE_DIRETTORE")) return TipoMansione.ROLE_DIRETTORE;
    	else if(mans.equals("ROLE_CASSIERE")) return TipoMansione.ROLE_CASSIERE;
    	else if(mans.equals("ROLE_MAGAZZINIERE_BANCO")) return TipoMansione.ROLE_MAGAZZINIERE_BANCO;
    	else if(mans.equals("ROLE_MAGAZZINIERE_SCAFFALE")) return TipoMansione.ROLE_MAGAZZINIERE_SCAFFALE;
    	else if(mans.equals("ROLE_RESPONSABILE")) return TipoMansione.ROLE_RESPONSABILE;
    	else if(mans.equals("ROLE_MANAGER")) return TipoMansione.ROLE_MANAGER;
    	else return TipoMansione.ROLE_PULIZIA;
    }
    
    private TipoContratto getTipologiaContratto(String contratto) {
    	if(contratto.equalsIgnoreCase("TEMPO_DETERMINATO")) return TipoContratto.TEMPO_DETERMINATO;
    	else if(contratto.equalsIgnoreCase("TEMPO_INDETERMINATO")) return TipoContratto.TEMPO_INDETERMINATO;
    	else if(contratto.equalsIgnoreCase("STAGE")) return TipoContratto.STAGE;
    	else if(contratto.equalsIgnoreCase("APPRENDISTATO")) return TipoContratto.APPRENDISTATO;
    	else return TipoContratto.CHIAMATA;
    }
    
}
