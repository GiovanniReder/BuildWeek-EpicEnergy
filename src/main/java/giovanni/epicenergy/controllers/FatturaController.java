package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Fattura;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.payloads.fatture.NuovaStatoFatturaDTO;
import giovanni.epicenergy.payloads.filtri.DataInserimentoDTO;
import giovanni.epicenergy.payloads.filtri.FatturaPerAnnoDTO;
import giovanni.epicenergy.payloads.filtri.FatturatoDTO;
import giovanni.epicenergy.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping("/stato")
    public Page<Fattura> filterByStatoFattura(@RequestBody @Validated NuovaStatoFatturaDTO body,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "data") String sortBy,
                                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return fatturaService.fatturaPerStato(body, page, size, sortBy);
    }

    @GetMapping("/data")
    public Page<Fattura> filterByData(@RequestBody @Validated DataInserimentoDTO body,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "data") String sortBy,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return fatturaService.fatturaPerData(body, page, size, sortBy);
    }

    @GetMapping("/importo")
    public Page<Fattura> filterByImporto(
            @RequestBody @Validated FatturatoDTO body,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "importo") String sortBy,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return fatturaService.fatturaPerImporto(body, page, size, sortBy);
    }

    @GetMapping("/anno")
    public Page<Fattura> filterByAnno(
            @RequestBody @Validated FatturaPerAnnoDTO body,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "importo") String sortBy,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return fatturaService.fatturaPerAnno(body, page, size, sortBy);
    }


}
    
     