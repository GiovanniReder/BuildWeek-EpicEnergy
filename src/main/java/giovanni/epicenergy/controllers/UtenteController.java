package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.payloads.ruoli.NuovoRuoloResponseDTO;
import giovanni.epicenergy.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController  {
    @Autowired
    private UtenteService utenteService;

@PatchMapping("/me/avatar") //DA RIVEDERE
    public Utente uploadAvatar(@AuthenticationPrincipal Utente currentUser , @RequestParam("avatar") MultipartFile image ) throws IOException {
    return this.utenteService.patchAvatarUtente(currentUser , this.utenteService.uploadAvatar(image));
} //ricava utente corrente
    @PatchMapping("/{userId}/ruolo")
    // DA SISTEMARE LA VALIDAZION E RESPONSE
    public Utente uploadRuolo( @PathVariable UUID userId , @RequestBody NuovoRuoloResponseDTO ruolo)
    {
        return utenteService.patchRuolo(ruolo , userId);


    }
}
