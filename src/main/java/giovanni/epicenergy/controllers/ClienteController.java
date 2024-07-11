package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Cliente;
import giovanni.epicenergy.payloads.clienti.ClienteFatturaDTO;
import giovanni.epicenergy.payloads.clienti.NuovoClienteDTO;
import giovanni.epicenergy.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/register")
    public Cliente save(@RequestBody @Validated NuovoClienteDTO body){
        return clienteService.save(body);
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
