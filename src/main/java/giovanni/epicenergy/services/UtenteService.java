package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.enums.TipoUtenteENUM;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.payloads.NuovoUtenteDTO;
import giovanni.epicenergy.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente save(NuovoUtenteDTO body) {
        this.utenteRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new RuntimeException("errore");
                }
        );

        Utente newUser = new Utente(body.ruolo() , body.cognome(), body.nome(), bcrypt.encode(body.password()), body.email(), body.userName());
        return utenteRepository.save(newUser);
    }

    public Utente findById(UUID utenteId){
        return utenteRepository.findById(utenteId).orElseThrow(()-> new NotFoundException(utenteId));
    }
}





