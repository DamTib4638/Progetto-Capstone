package alimentari.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mansioni")
public class Mansione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMansione;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
	private TipoMansione tipoMansione;
}

