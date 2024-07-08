package giovanni.epicenergy.entities;

import jakarta.persistence.Column;
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
    @Column(name = "lista_stati")
    private List<String> listaStati;
    @Column(name = "stato_attuale")
    private String statoAttuale;

}
