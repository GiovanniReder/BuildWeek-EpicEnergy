package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.payloads.NuovoUtenteDTO;
import giovanni.epicenergy.payloads.NuovoUtenteResponseDTO;
import giovanni.epicenergy.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/utenti")
public class UtenteController  {
    @Autowired
    private UtenteService utenteService;

@PatchMapping("/me/avatar") //DA RIVEDERE
    public Utente uploadAvatar(@AuthenticationPrincipal Utente currentUser , @RequestParam("avatar") MultipartFile image ) throws IOException {
    return this.utenteService.patchAvatarUtente(currentUser , this.utenteService.uploadAvatar(image));
} //ricava utente corrente



}
