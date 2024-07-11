package giovanni.epicenergy.services;

import giovanni.epicenergy.controllers.ClienteController;
import giovanni.epicenergy.entities.Cliente;
import giovanni.epicenergy.entities.Comune;
import giovanni.epicenergy.entities.Indirizzo;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.payloads.clienti.ClienteFatturaDTO;
import giovanni.epicenergy.payloads.clienti.NuovoClienteDTO;
import giovanni.epicenergy.repositories.ClienteRepository;
import giovanni.epicenergy.repositories.IndirizzoRepository;
import giovanni.epicenergy.repositories.comuni_e_province.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private IndirizzoRepository indirizzoRepository;
    @Autowired
    private ComuneService comuneService;


    public Cliente save(NuovoClienteDTO body){
        Cliente newCliente = new Cliente(body.ragioneSociale(), body.partitaIva(), body.email(), body.dataInserimento(),
                body.dataUltimoContatto(), body.fatturatoAnnuale(), body.pec(), body.emailContatto(), body.nomeContatto(),
                body.telefonoContatto(), body.logoAziendale());
        clienteRepository.save(newCliente);
        Indirizzo indirizzo = new Indirizzo(body.comune(), body.cap(), body.civico(), body.via());
        Comune comune = comuneService.findComuneByCapAndName(String.valueOf(body.cap()), body.comune());
        indirizzo.setLocalità(indirizzo.getCap() + ", " + indirizzo.getComune() + ", " + comune.getProvincia().getSigla());
        indirizzo.setIndirizzoCliente(newCliente);
        indirizzoRepository.save(indirizzo);
        newCliente.addSede(indirizzo);
        clienteRepository.save(newCliente);
        return newCliente;
    }

    public Cliente findById(UUID clienteId){
        return clienteRepository.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
    }

    public Page<Cliente> getAll(int pageNumber, int pageSize, String sortBy){
        if(pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.clienteRepository.findAll(pageable);
    }

    public Page<ClienteFatturaDTO> getAllFatturati(int pageNumber, int pageSize, String sortBy){
        if(pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.clienteRepository.findAll(pageable).map(cliente -> new ClienteFatturaDTO(cliente.getFatturatoAnnuale()));
    }
}