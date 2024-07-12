package giovanni.epicenergy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ruoli")
@NoArgsConstructor
@JsonIgnoreProperties(value = {"id"})
public  class RuoloUtente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;
    private String ruolo;

    public RuoloUtente(String ruolo) {
        this.ruolo = ruolo;
    }

    //CRAERE METODO ADDRUOLO

}
