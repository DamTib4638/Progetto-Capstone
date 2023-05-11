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
@Table(name = "dettagli_scarico")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class DettaglioScarico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDettaglioScarico;
	
	private Integer qtaAcquisto;
	private Double pesoAcquisto;
	
	@Column(nullable = false)
	@Min(0)
	private Double prezzoAcquisto;
	
	@Column(nullable = false)
	@Min(0)
	private Double prezzoUnitario;
	
	@ManyToOne
	@JoinColumn(name = "idScarico")
	private Scarico scarico;
	
	@ManyToOne
	@JoinColumn(name = "idProdotto")
	private Prodotto prodotto;

}
