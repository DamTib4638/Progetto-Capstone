package alimentari.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "prodotti")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Prodotto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProdotto;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String marca;
	
	private String descrizione;
	
	@Column(nullable = false)
	private String ingredienti;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoProdotto tipoProdotto;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private CategoriaProdotto categoriaProdotto;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataScadenza;
	
	private Integer qtaDisponibile;
	
	private Double pesoDisponibile;
	
	@Column(nullable = false)
	@Min(0)
	private Double prezzoAcquistoUnitario;
	
	@Column(nullable = false)
	@Min(0)
	private Double prezzoVenditaUnitario;
	
	private Integer percentualeOfferta;
	
	@ManyToOne
	@JoinColumn(name = "idScaffale")
	private Scaffale scaffale;
	
//	@OneToMany(mappedBy = "prodotto", cascade = CascadeType.REMOVE)
//	private List<DettaglioScarico> dettagliScarico;
//	
//	@OneToMany(mappedBy = "prodotto")
//	private List<DettaglioTransazione> dettagliTransazione;

}
