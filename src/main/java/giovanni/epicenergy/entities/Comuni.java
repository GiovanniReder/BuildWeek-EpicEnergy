package giovanni.epicenergy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "comuni")
@Getter
@Setter
@ToString

@NoArgsConstructor
public class Comuni {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;
    private long codiceProvincia;
    private String nomeProvincia;
    private short cap;
    private String nomeComune;

    public Comuni(long codiceProvincia, String nomeProvincia, short cap, String nomeComune) {
        this.codiceProvincia = codiceProvincia;
        this.nomeProvincia = nomeProvincia;
        this.cap = cap;
        this.nomeComune = nomeComune;
    }
}
