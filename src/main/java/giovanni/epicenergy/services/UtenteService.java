package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Utente save(NewUserDTO body) {
        this.utenteRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );

        User newUser = new User(body.name(), body.surname(), body.email(), bcrypt.encode(body.password()) , role(body) );



        return usersRepository.save(newUser);
    }


}
