package alimentari.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FornitoreDTO {
	
	private Long id;
	private String nomeDittaForn;
	private String indirizzo;
	private String citta;
	private String dataInizioRapporto;
	private String dataFineRapporto;
	private String email;
	private String telefono;

}
