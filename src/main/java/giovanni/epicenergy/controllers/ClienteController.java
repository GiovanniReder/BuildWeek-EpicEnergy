package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Cliente;
import giovanni.epicenergy.payloads.clienti.NuovoClienteDTO;
import giovanni.epicenergy.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente save(@RequestBody NuovoClienteDTO body){
        return clienteService.save(body);
    }

//    @PatchMapping("/fattura")
//    public Cliente addFattura(@RequestBody )
}
