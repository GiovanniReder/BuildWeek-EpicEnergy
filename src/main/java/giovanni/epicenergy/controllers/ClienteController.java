package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Cliente;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.payloads.NuovoIndirizzoDTO;
import giovanni.epicenergy.payloads.clienti.ClienteFatturaDTO;
import giovanni.epicenergy.payloads.clienti.NuovoClienteDTO;
import giovanni.epicenergy.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Validated NuovoClienteDTO body, BindingResult validationResult ){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }

        return clienteService.save(body);
    }

    @PostMapping("/{clienteId}/indirizzo")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente addIndirizzo(@PathVariable UUID clienteId, @RequestBody NuovoIndirizzoDTO body){
        return clienteService.addIndirizzo(clienteId, body);
    }

    @PutMapping("/{clienteId}/{indirizzoId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Cliente updateIndirizzo(@PathVariable UUID clienteId, @PathVariable UUID indirizzoId, @RequestBody NuovoIndirizzoDTO body){
        return clienteService.updateIndirizzo(clienteId,indirizzoId,body);
    }

    @DeleteMapping("/{clienteId}/{indirizzoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteIndirizzo(@PathVariable UUID clienteId, @PathVariable UUID indirizzoId){
         clienteService.deleteIndirizzo(clienteId,indirizzoId);
    }


    @GetMapping
    public Page<Cliente> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return this.clienteService.getAll(page, size, sortBy);
    }

    @GetMapping("/fatturati")
    public Page<ClienteFatturaDTO> getAllFatturati(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return this.clienteService.getAllFatturati(page, size, sortBy);
    }
}
