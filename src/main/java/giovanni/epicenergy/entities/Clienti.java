package giovanni.epicenergy.entities;

import giovanni.epicenergy.enums.RagioneSocialeENUM;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@ToString

@NoArgsConstructor
public class Clienti {

    @Id
    @GeneratedValue
    private UUID id;

    private RagioneSocialeENUM ragioneSociale;

    private long partitaIva;
    private String email;
    
}
