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
@Table(name = "scaffali")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Scaffale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idScaffale;
	
	private String nome;
	
	@Column(nullable = false)
	@Min(0)
	private Integer posizione;
	
	@Column(nullable = false)
	@Min(0)
	private Integer numeroMaxProd;
	
	@Column(nullable = false)
	@Min(0)
	private Double pesoMaxProd;
	
	@ManyToOne
	@JoinColumn(name = "idReparto", nullable = false)
	private Reparto reparto;

}
