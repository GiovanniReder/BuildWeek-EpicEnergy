package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.exceptions.UnauthorizedException;
import giovanni.epicenergy.payloads.utenti.UtenteLoginDTO;
import giovanni.epicenergy.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UtenteLoginDTO payload){
        Utente found = utenteService.findByMail(payload.email());
        if (bcrypt.matches(payload.password(), found.getPassword())){
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali non corrette");
        }
    }
}
