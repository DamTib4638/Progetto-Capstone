package alimentari.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DettaglioScaricoDTO {
	
	private Integer qtaAcquisto;
	private Double pesoAcquisto;
	private Double prezzoAcquisto;
	private Long idScarico;
	private Long idProdotto;

}
