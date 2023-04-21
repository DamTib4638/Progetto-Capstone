package alimentari.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScaffaleDTO {
	
	private String nome;
	private Integer posizione;
	private Integer numMaxProd;
	private Double pesoMaxProd;
	private Long idReparto;

}
