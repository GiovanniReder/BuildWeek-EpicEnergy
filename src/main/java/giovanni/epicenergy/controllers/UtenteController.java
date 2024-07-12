package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.payloads.ruoli.NuovoRuoloResponseDTO;
import giovanni.epicenergy.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PatchMapping("/me/avatar")
    public Utente uploadAvatar(@AuthenticationPrincipal Utente currentUser , @RequestParam("avatar") MultipartFile image ) throws IOException {
    return this.utenteService.patchAvatarUtente(currentUser , this.utenteService.uploadAvatar(image));
    }

    //ricava utente corrente
    //TODO DA SISTEMARE LA VALIDAZION E RESPONSE
    @PatchMapping("/{userId}/ruolo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente uploadRuolo( @PathVariable UUID userId , @RequestBody NuovoRuoloResponseDTO ruolo) {
        return utenteService.patchRuolo(ruolo , userId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return  this.utenteService.getAllEvent(page, size, sortBy);
    }

    
                        //creare metodo per aggiungere un ruolo
}
