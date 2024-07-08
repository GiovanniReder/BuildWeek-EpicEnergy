package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.payloads.NuovoUtenteDTO;
import giovanni.epicenergy.payloads.NuovoUtenteResponseDTO;
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

    // POST
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NuovoUtenteResponseDTO userResponseDTO (@RequestBody NuovoUtenteDTO body ){

        return new NuovoUtenteResponseDTO(this.utenteService.save(body).getId());
    }

}
