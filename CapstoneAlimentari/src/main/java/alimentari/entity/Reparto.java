package alimentari.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "reparti")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Reparto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReparto;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoReparto tipoReparto;
	
	private String descrizione;

}
