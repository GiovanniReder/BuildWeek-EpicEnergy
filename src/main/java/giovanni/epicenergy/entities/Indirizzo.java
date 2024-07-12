package giovanni.epicenergy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@NoArgsConstructor
@JsonIgnoreProperties(value = { "id" })
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "clientiID")
    private Cliente indirizzoCliente;

    private String via;
    private String civico;
    private String località;
    private String cap;
    private String comune;

    public Indirizzo(String comune, String cap, String civico, String via) {
        this.comune = comune;
        this.cap = cap;
        this.civico = civico;
        this.via = via;
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "id=" + id +
                ", via='" + via + '\'' +
                ", civico=" + civico +
                ", località='" + località + '\'' +
                ", cap=" + cap +
                ", comune='" + comune + '\'' +
                '}';
    }
}
