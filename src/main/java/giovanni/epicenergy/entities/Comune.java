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
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;
    @Column(name = "codice_provincia")
    private long codiceProvincia;
    @Column(name = "nome_provincia")
    private String nomeProvincia;
    private short cap;
    @Column(name = "nome_comune")
    private String nomeComune;

    public Comune(long codiceProvincia, String nomeProvincia, short cap, String nomeComune) {
        this.codiceProvincia = codiceProvincia;
        this.nomeProvincia = nomeProvincia;
        this.cap = cap;
        this.nomeComune = nomeComune;
    }
}
