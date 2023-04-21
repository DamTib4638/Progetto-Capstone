package alimentari.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "casse")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Cassa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCassa;
	
	@Column(nullable = false, unique = true)
	@Min(1)
	private Integer numeroCassa;
	
	@Min(0)
	private Double fondoCassa;
	
	@Column(nullable = false)
	private Boolean cassaFunzionante;

}
