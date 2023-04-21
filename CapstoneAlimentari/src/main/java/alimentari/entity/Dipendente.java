package alimentari.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "dipendenti", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDipendente;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private String cognome;
    
    @Column(nullable = false)
    private Integer eta;
    
    @Column(nullable = false)
    private String codFis;
    
    private String telefono;
    private String indirizzo;
    private String citta;
    
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "mansioniDipendenti",
            joinColumns = @JoinColumn(name = "idDipendente", referencedColumnName = "idDipendente"),
            inverseJoinColumns = @JoinColumn(name = "idMansione", referencedColumnName = "idMansione")
    )
    private Set<Mansione> mansioni = new HashSet<>();
    
//    @OneToMany(mappedBy = "dipendente", cascade = CascadeType.REMOVE)
//    private List<ContrattoDipendente> contratti = new ArrayList<ContrattoDipendente>();
}
