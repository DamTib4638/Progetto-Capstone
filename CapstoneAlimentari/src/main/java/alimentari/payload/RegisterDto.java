package alimentari.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String nome;
    private String cognome;
    private Integer eta;
    private String codFis;
    private String telefono;
    private String indirizzo;
    private String citta;
    private String email;
    private String password;
    private Set<String> mansioni;
    private String contratto;
    private String dataInizioContratto;
    private String dataFineContratto;
}
