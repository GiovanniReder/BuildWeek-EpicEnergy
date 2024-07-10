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
    @Column(name = "nome_provincia")
    private String nomeProvincia;
    private String cap;
    @Column(name = "nome_comune")
    private String nomeComune;

    @ManyToOne
    @JoinColumn(name = "provincia_id")
    private Provincia provincia;

    public Comune(String nomeProvincia, String nomeComune, String cap) {
        this.nomeProvincia = nomeProvincia;
        this.cap = cap;
        this.nomeComune = nomeComune;
    }
}
