package alimentari.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "contratti")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Contratto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idContratto;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private TipoContratto tipoContratto;
	
	@Column(nullable = false)
	@Min(5)
	private Double pagaOraria;
	
	@Column(nullable = false)
	@Min(240)
	@Max(480)
	private Integer minutiGiornalieriTurno;
	
	@Column(nullable = false)
	@Min(1200)
	@Max(2400)
	private Integer minutiSettimanaliTurno;
	
	@Column(nullable = false)
	@Min(10)
	private Double percentualeMaggFestivo;
	
	@Column(nullable = false)
	@Min(6)
	private Double pagaOrariaStraordinario;
	
	@Column(nullable = false)
	@Min(28800)
	@Max(57600)
	private Integer minutiMalattiaAnno;
	
	@Column(nullable = false)
	@Min(4800)
	@Max(9600)
	private Integer minutiFerieAnno;
	
	@Column(nullable = false)
	@Min(4800)
	@Max(9600)
	private Integer minutiPermessoAnno;

}
