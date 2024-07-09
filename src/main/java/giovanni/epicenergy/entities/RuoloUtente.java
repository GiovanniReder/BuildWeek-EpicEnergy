package giovanni.epicenergy.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ruoli")
@NoArgsConstructor


public  class RuoloUtente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;
/*
@ManyToOne
@JoinColumn(name = "utente_id")
private Utente utente;
* */
    private String ruolo;

    public RuoloUtente(String ruolo) {
        this.ruolo = ruolo;
    }

    //CRAERE METODO ADDRUOLO



}
