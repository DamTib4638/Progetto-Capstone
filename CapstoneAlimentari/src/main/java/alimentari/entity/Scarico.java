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
@Table(name = "scarichi")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Scarico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idScarico;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataPrenScar;
	
	@Temporal(TemporalType.DATE)
	private Date dataRegScar;
	
	@ManyToOne
	@JoinColumn(name = "idFornitore")
	private Fornitore fornitore;
	
	@ManyToOne
	@JoinColumn(name = "idDipendente")
	private Dipendente dipendente;
	
//	@OneToMany(mappedBy = "scarico", cascade = CascadeType.REMOVE)
//	private List<DettaglioScarico> dettagliScarico;

}
