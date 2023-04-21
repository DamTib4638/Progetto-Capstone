package alimentari.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "contratti_dipendenti")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class ContrattoDipendente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContrattoDipendente;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataInizio;
	
	@Temporal(TemporalType.DATE)
	private Date dataFine;
	
	@ManyToOne
	@JoinColumn(name = "idContratto")
	private Contratto contratto;
	
	@ManyToOne
	@JoinColumn(name = "idDipendente")
	private Dipendente dipendente;

}
