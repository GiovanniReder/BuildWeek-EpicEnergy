package giovanni.epicenergy.controllers;

import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.payloads.utenti.NuovoUtenteDTO;
import giovanni.epicenergy.payloads.utenti.NuovoUtenteResponseDTO;
import giovanni.epicenergy.payloads.utenti.UtenteLoginDTO;
import giovanni.epicenergy.payloads.utenti.UtenteLoginResponseDTO;
import giovanni.epicenergy.services.AuthService;
import giovanni.epicenergy.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
