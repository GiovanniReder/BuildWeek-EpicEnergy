package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.payloads.NuovoUtenteDTO;
import giovanni.epicenergy.payloads.NuovoUtenteResponseDTO;
import giovanni.epicenergy.payloads.UtenteLoginDTO;
import giovanni.epicenergy.payloads.UtenteLoginResponseDTO;
import giovanni.epicenergy.services.AuthService;
import giovanni.epicenergy.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private AuthService authService;

    // http://localhost:3001/auth/register

    // POST
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NuovoUtenteResponseDTO userResponseDTO (@RequestBody @Validated NuovoUtenteDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new NuovoUtenteResponseDTO(this.utenteService.save(body).getId());
    }
    //LOGIN  http://localhost:3001/auth/login
    @PostMapping("/login")
    public UtenteLoginResponseDTO login(@RequestBody @Validated UtenteLoginDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new UtenteLoginResponseDTO(this.authService.authenticateUserAndGenerateToken(body));
    }

}
