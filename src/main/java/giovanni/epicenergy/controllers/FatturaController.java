package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Fattura;
import giovanni.epicenergy.payloads.fatture.NuovaStatoFatturaDTO;
import giovanni.epicenergy.payloads.filtri.DataInserimentoDTO;
import giovanni.epicenergy.payloads.filtri.FatturaPerAnnoDTO;
import giovanni.epicenergy.payloads.filtri.FatturatoDTO;
import giovanni.epicenergy.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fatture")
public class FatturaController {
    @Autowired
    private FatturaService fatturaService;

    @GetMapping("/stato")
    public Page<Fattura> filterByStatoFattura(@RequestBody NuovaStatoFatturaDTO body,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String sortBy) {
        return fatturaService.fatturaPerStato(body, page, size, sortBy);
    }

    @GetMapping("/data")
    public Page<Fattura> filterByData(@RequestBody DataInserimentoDTO body,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "data") String sortBy) {
        return fatturaService.fatturaPerData(body, page, size, sortBy);
    }

    @GetMapping("/importo")
    public Page<Fattura> filterByImporto(
            @RequestBody FatturatoDTO body,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "importo") String  sortBy
            ){
        return fatturaService.fatturaPerImporto(body,page, size, sortBy);
    }
    @

    public Page<Fattura> filterByAnno(
            @RequestBody FatturaPerAnnoDTO body,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "importo") String  sortBy
            ){ r n fatturaService.fatturaPerAnno(body,page,size,sortBy);
    }   


    

    
     