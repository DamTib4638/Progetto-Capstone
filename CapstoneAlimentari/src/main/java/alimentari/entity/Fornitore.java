package alimentari.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "fornitori")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Fornitore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFornitore;
	
	@Column(nullable = false)
	private String nomeDittaFornitore;
	
	private String indirizzo;
	private String citta;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date dataInizioRapporto;
	
	@Temporal(TemporalType.DATE)
	private Date dataFineRapporto;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String telefono;

}
