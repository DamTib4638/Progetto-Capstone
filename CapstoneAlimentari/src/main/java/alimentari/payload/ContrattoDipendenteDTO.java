package alimentari.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContrattoDipendenteDTO {
	
	private String dataInizio;
	private String dataFine;
	private Long idContratto;
	private Long idDipendente;

}
