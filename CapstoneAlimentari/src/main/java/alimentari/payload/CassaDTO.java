package alimentari.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CassaDTO {
	
	private Long id;
	private Integer numCassa;
	private Double fondoCassa;
	private Integer cassaFunzionante;

}
