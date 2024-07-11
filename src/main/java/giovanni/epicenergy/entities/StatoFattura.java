package giovanni.epicenergy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;


@Entity
@Table(name = "stato_fattura")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(value = {  "id" })
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;

    private String stato;

    public StatoFattura(String stato) {
        this.stato = stato;
    }
}
