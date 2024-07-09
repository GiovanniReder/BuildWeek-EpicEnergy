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


    private String ruolo;

    public RuoloUtente(String ruolo) {
        this.ruolo = "USER";
    }

    //CRAERE METODO ADDRUOLO



}
