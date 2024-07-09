package giovanni.epicenergy.entities;

import giovanni.epicenergy.enums.TipoUtenteENUM;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Utente implements UserDetails  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private UUID id;
    private String userName;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utenti_ruoli",
    joinColumns = @JoinColumn(name = "utenti_id") , inverseJoinColumns =@JoinColumn(name = "ruoli_id"))
    private List<RuoloUtente> ruoli= new ArrayList<>();


    public Utente(String cognome, String nome, String password, String email, String userName) {
        this.cognome = cognome;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.userName = userName;

        this.avatar = "https://ui-avatars.com/api/?name=" + this.nome + "+" + this.cognome ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.ruoli.stream()
                .map(ruolo -> new SimpleGrantedAuthority(ruolo.getRuolo()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }


}
