package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Fattura;
import giovanni.epicenergy.entities.StatoFattura;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.payloads.fatture.NuovaFatturaDTO;
import giovanni.epicenergy.payloads.fatture.NuovaStatoFatturaDTO;
import giovanni.epicenergy.services.FatturaService;
import giovanni.epicenergy.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/fatture")
public class StatoFatturaController {
    @Autowired
    private StatoFatturaService statoFatturaService;
    @Autowired
    private FatturaService fatturaService;

    @PostMapping("/stato")
    @PreAuthorize("hasAuthority('ADMIN')")
    public StatoFattura saveStatoFattura(@RequestBody @Validated NuovaStatoFatturaDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return statoFatturaService.save(body);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura saveFattura(@RequestBody @Validated NuovaFatturaDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return fatturaService.save(body);
    }

    @PatchMapping("/{fatturaId}/stato")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura patchStatoFattura(@RequestBody @Validated  NuovaStatoFatturaDTO body , @PathVariable UUID fatturaId, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return fatturaService.patchStatoFattura(body , fatturaId);
    }
}
