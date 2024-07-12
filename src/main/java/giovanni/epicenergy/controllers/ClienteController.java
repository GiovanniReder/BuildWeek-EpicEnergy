package giovanni.epicenergy.controllers;

import giovanni.epicenergy.entities.Cliente;

import giovanni.epicenergy.entities.Fattura;
import giovanni.epicenergy.entities.Indirizzo;
import giovanni.epicenergy.exceptions.BadRequestException;

import giovanni.epicenergy.payloads.NuovoIndirizzoDTO;
import giovanni.epicenergy.payloads.clienti.ClienteResponseDTO;
import giovanni.epicenergy.payloads.clienti.NuovoClienteDTO;

import giovanni.epicenergy.payloads.clienti.NuovoClienteResponseDTO;
import giovanni.epicenergy.payloads.clienti.NuovoIndirizzoResponseDTO;
import giovanni.epicenergy.payloads.filtri.ClientePerNomeDTO;
import giovanni.epicenergy.payloads.filtri.DataInserimentoDTO;
import giovanni.epicenergy.payloads.filtri.DataUltimoContattoDTO;
import giovanni.epicenergy.payloads.filtri.FatturatoDTO;

import giovanni.epicenergy.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/register")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public NuovoClienteResponseDTO save(@RequestBody @Validated NuovoClienteDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return new NuovoClienteResponseDTO(clienteService.save(body).getId());
    }

    @PostMapping("/{clienteId}/indirizzo")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Indirizzo addIndirizzo(@PathVariable UUID clienteId, @RequestBody @Validated NuovoIndirizzoDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return clienteService.addIndirizzo(clienteId, body);
    }

    @PutMapping("/indirizzi/{indirizzoId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Indirizzo updateIndirizzo( @PathVariable UUID indirizzoId, @RequestBody @Validated NuovoIndirizzoDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return clienteService.updateIndirizzo(indirizzoId,body);
    }

    @PutMapping("/{clienteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ClienteResponseDTO updateCliente( @PathVariable UUID clienteId, @RequestBody @Validated NuovoClienteDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return clienteService.updateCliente(clienteId,body);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCliente( @PathVariable UUID clienteId){
        clienteService.deleteCliente(clienteId);
    }

    @DeleteMapping("/indirizzi/{indirizzoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteIndirizzo( @PathVariable UUID indirizzoId){
         clienteService.deleteIndirizzo(indirizzoId);
    }


    @GetMapping
    public Page<ClienteResponseDTO> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return this.clienteService.getAll(page, size, sortBy);
    }

    @GetMapping("/provincia")
    public Page<ClienteResponseDTO> getAllProvinciaSedeLegale(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "sedi.località") String sortBy){
        return this.clienteService.getAll(page, size, sortBy);
    }

    @GetMapping("/fatturati")
    public Page<ClienteResponseDTO> getAllByFatturato(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "fatturatoAnnuale") String sortBy){
        return this.clienteService.getAll(page, size, sortBy);
    }

    @GetMapping("/nomi")
    public Page<ClienteResponseDTO> getAllByNome(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "nomeContatto") String sortBy){
        return this.clienteService.getAll(page, size, sortBy);
    }

    @GetMapping("/dataInserimento")
    public Page<ClienteResponseDTO> getAllByDataInserimento(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "dataInserimento") String sortBy){
        return this.clienteService.getAll(page, size, sortBy);
    }

    @GetMapping("/dataUltimoContatto")
    public Page<ClienteResponseDTO> getAllByDataUltimoContatto(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "dataUltimoContatto") String sortBy){
        return this.clienteService.getAll(page, size, sortBy);
    }

    @GetMapping("/filter/fatturatoAnnuale")
    public Page<Cliente> filterByFatturatoAnnuale(@RequestBody @Validated FatturatoDTO body,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "fatturatoAnnuale") String  sortBy,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.clienteService.filterByFatturatoAnnuale(body, page, size, sortBy);
    }

    @GetMapping("/filter/dataInserimento")
    public Page<Cliente> filterByDataInserimento(@RequestBody @Validated DataInserimentoDTO body,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "dataInserimento") String  sortBy,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.clienteService.filterByDataInserimento(body, page, size, sortBy);
    }

    @GetMapping("/filter/dataUltimoContatto")
    public Page<Cliente> filterByDataUltimoContatto(@RequestBody @Validated DataUltimoContattoDTO body,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size,
                                                    @RequestParam(defaultValue = "dataUltimoContatto") String  sortBy,
                                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.clienteService.filterByDataUltimoContatto(body, page, size, sortBy);
    }

    @GetMapping("filter/nomeContatto")
    public Page<Cliente> filterByNomeContatto(@RequestBody @Validated ClientePerNomeDTO body,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "nomeContatto") String  sortBy,
                                              BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return this.clienteService.filterByNomeContatto(body,page,size,sortBy);
    }

    // FILTRI PER FATTURE
    @GetMapping("/{clienteId}/fatture")
    public Page<Fattura> filterFattureByCliente(
            @PathVariable UUID clienteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String  sortBy){
       return this.clienteService.filterFattureByCliente(clienteId, page, size, sortBy);
    }

}
