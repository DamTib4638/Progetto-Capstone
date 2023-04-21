package alimentari.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContrattoDTO {
	
	private String tipoContratto;
	private Double pagaOraria;
	private Integer minGiornoTurno;
	private Integer minSettTurno;
	private Double percMaggFest;
	private Double pagaOrariaStr;
	private Integer minMalattiaAnno;
	private Integer minFerieAnno;
	private Integer minPermessoAnno;

}
