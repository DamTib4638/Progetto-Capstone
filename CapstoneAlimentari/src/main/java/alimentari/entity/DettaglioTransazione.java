package alimentari.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "dettagli_transazione")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class DettaglioTransazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDettaglioTransazione;
	
	private Integer qtaVendita;
	private Double pesoVendita;
	
	@Column(nullable = false)
	@Min(0)
	private Double prezzoVendita;
	
	@ManyToOne
	@JoinColumn(name = "idTransazione")
	private Transazione transazione;
	
	@ManyToOne
	@JoinColumn(name = "idProdotto")
	private Prodotto prodotto;

}
