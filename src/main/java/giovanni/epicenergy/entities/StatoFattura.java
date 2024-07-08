package giovanni.epicenergy.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@ToString

@NoArgsConstructor
public class StatoFattura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;

    private List<String> listaStati;
    private String statoAttuale;

}
