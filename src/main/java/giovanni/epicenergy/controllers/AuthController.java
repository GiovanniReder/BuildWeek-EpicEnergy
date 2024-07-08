package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.payloads.NuovoUtenteDTO;
import giovanni.epicenergy.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    // http://localhost:3001/auth/register

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente userResponseDTO (@RequestBody NuovoUtenteDTO body ){

        return this.utenteService.save(body);
    }

}
