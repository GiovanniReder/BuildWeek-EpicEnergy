package giovanni.epicenergy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "indirizzi")
@Getter
@Setter
@ToString

@NoArgsConstructor
public class Indirizzi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "clientiID")
    private Clienti indirizzoCliente;

    private String via;
    private long civico;
    private String località;
    private long cap;
    private String comune;

    public Indirizzi(String comune, long cap, String località, long civico, String via) {
        this.comune = comune;
        this.cap = cap;
        this.località = località;
        this.civico = civico;
        this.via = via;
    }
}
