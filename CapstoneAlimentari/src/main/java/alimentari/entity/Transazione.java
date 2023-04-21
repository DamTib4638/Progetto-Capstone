package alimentari.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "transazioni")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Transazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTransazione;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataEmissioneTransazione;
	
	@Temporal(TemporalType.DATE)
	private Date dataPagamentoTransazione;
	
	@ManyToOne
	@JoinColumn(name = "idCassa")
	private Cassa cassa;
	
	@ManyToOne
	@JoinColumn(name = "idDipendente")
	private Dipendente dipendente;
	
//	@OneToMany(mappedBy = "transazione", cascade = CascadeType.REMOVE)
//	private List<DettaglioTransazione> dettagliTransazione;

}
