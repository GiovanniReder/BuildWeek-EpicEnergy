package giovanni.epicenergy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giovanni.epicenergy.enums.RagioneSocialeENUM;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "sedi" })
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;

    @Column(name = "ragione_sociale")
    private RagioneSocialeENUM ragioneSociale;
    @Column(name = "partita_iva")
    private long partitaIva;
    private String email;
    @Column(name = "data_inserimento")
    private LocalDate dataInserimento;
    @Column(name = "data_ultimo_contatto")
    private LocalDate dataUltimoContatto;
    @Column(name = "fatturato_annuale")
    private long fatturatoAnnuale;
    private String pec;
    @Column(name = "email_contatto")
    private String emailContatto;
    @Column(name = "nome_contatto")
    private String nomeContatto;
    @Column(name = "telefono_contatto")
    private long telefonoContatto;
    @Column(name = "logo_aziendale")
    private String logoAziendale;

    @OneToMany(mappedBy = "indirizzoCliente")
    @Column(name = "sedi")
    private List<Indirizzo> sedi;

    @OneToMany(mappedBy = "clienti")
    @Column(name = "lista_fatture")
    private List<Fattura> listaFatture;

    public Cliente(RagioneSocialeENUM ragioneSociale, long partitaIva, String email, LocalDate dataInserimento, LocalDate dataUltimoContatto, long fatturatoAnnuale, String pec, String emailContatto, String nomeContatto, long telefonoContatto, String logoAziendale) {
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
        this.sedi = new ArrayList<>();
    }

    public void addSede(Indirizzo indirizzo){
        sedi.add(indirizzo);
    }
}
