package giovanni.epicenergy.entities;

import giovanni.epicenergy.enums.RagioneSocialeENUM;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@ToString

@NoArgsConstructor
public class Clienti {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;

    private RagioneSocialeENUM ragioneSociale;
    private long partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private long fatturatoAnnuale;
    private String pec;
    private String emailContatto;
    private String nomeContatto;
    private long telefonoContatto;
    private String logoAziendale;

    @OneToMany(mappedBy = "indirizzoCliente")
    private List<Indirizzi> sedi;
    @OneToMany(mappedBy = "clienti")
    private List<Fatture> listaFatture;

    public Clienti(RagioneSocialeENUM ragioneSociale, long partitaIva, String email, LocalDate dataInserimento, LocalDate dataUltimoContatto, long fatturatoAnnuale, String pec, String emailContatto, String nomeContatto, long telefonoContatto, String logoAziendale, List<Fatture> listaFatture, List<Indirizzi> sedi) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;
        this.listaFatture = listaFatture;
        this.sedi = sedi;
    }
}
