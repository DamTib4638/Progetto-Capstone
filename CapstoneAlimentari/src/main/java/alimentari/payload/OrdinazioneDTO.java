package alimentari.payload;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrdinazioneDTO {
	
	private Date dataPrenotazioneOrdine;
	private Date dataEmissioneTransazione;
	private Long idFornitore;
	private Long idDipendente;
	private Long idCassa;
	private List<ProdottoDTO> listaProdotti;

}
