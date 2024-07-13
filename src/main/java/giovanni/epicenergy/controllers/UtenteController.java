package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.RuoloUtente;
import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.payloads.ruoli.NuovoRuoloDTO;
import giovanni.epicenergy.payloads.ruoli.NuovoRuoloResponseDTO;
import giovanni.epicenergy.services.RuoloUtenteService;
import giovanni.epicenergy.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController  {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private RuoloUtenteService ruoloUtenteService;

    @PatchMapping("/me/avatar")
    public Utente uploadAvatar(@AuthenticationPrincipal Utente currentUser , @RequestParam("avatar") MultipartFile image ) throws IOException {
    return this.utenteService.patchAvatarUtente(currentUser , this.utenteService.uploadAvatar(image));
    }

    @PatchMapping("/{userId}/ruolo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Utente addRuolo(@PathVariable UUID userId , @RequestBody @Validated NuovoRuoloResponseDTO ruolo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return utenteService.addRuolo(ruolo , userId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Utente> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return  this.utenteService.getAll(page, size, sortBy);
    }

    @PostMapping("/ruolo")
    @PreAuthorize("hasAuthority('ADMIN')")
      public RuoloUtente addRuolo(@RequestBody @Validated NuovoRuoloDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return ruoloUtenteService.save(body);
    }                 
}
