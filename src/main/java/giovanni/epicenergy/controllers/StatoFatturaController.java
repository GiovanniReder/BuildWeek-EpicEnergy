package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Fattura;
import giovanni.epicenergy.entities.StatoFattura;
import giovanni.epicenergy.payloads.fatture.NuovaFatturaDTO;
import giovanni.epicenergy.payloads.fatture.NuovaStatoFatturaDTO;
import giovanni.epicenergy.services.FatturaService;
import giovanni.epicenergy.services.StatoFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public StatoFattura saveStatoFattura(@RequestBody NuovaStatoFatturaDTO body){
       return statoFatturaService.save(body);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura saveFattura(@RequestBody NuovaFatturaDTO body){
        return fatturaService.save(body);
    }

    @PatchMapping("/{fatturaId}/stato")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Fattura patchStatoFattura(@RequestBody NuovaStatoFatturaDTO body , @PathVariable UUID fatturaId){
        return
                fatturaService.patchStatoFattura(body , fatturaId);
    }
}
