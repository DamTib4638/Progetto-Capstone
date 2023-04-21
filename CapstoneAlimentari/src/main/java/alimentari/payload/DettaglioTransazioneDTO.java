package alimentari.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DettaglioTransazioneDTO {
	
	private Integer qtaVendita;
	private Double pesoVendita;
	private Double prezzoVendita;
	private Long idTransazione;
	private Long idProdotto;

}
