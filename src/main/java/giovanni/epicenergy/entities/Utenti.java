package giovanni.epicenergy.entities;

import giovanni.epicenergy.enums.TipoUtenteENUM;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@ToString

@NoArgsConstructor

public class Utenti {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;

    private String userName;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String avatar; //DA SETTARE QUELLA STANDARD
    private TipoUtenteENUM ruolo; //INCOGNITA SE ENUM O NO

    public Utenti(TipoUtenteENUM ruolo, String avatar, String cognome, String nome, String password, String email, String userName) {
        this.ruolo = ruolo;
        this.avatar = avatar;
        this.cognome = cognome;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.userName = userName;
    }
}
