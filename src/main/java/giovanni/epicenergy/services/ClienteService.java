package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Cliente;
import giovanni.epicenergy.entities.Indirizzo;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.payloads.clienti.NuovoClienteDTO;
import giovanni.epicenergy.repositories.ClienteRepository;
import giovanni.epicenergy.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private IndirizzoRepository indirizzoRepository;

    public Cliente save(NuovoClienteDTO body){
        Cliente newCliente = new Cliente(body.ragioneSociale(), body.partitaIva(), body.email(), body.dataInserimento(),
                body.dataUltimoContatto(), body.fatturatoAnnuale(), body.pec(), body.emailContatto(), body.nomeContatto(),
                body.telefonoContatto(), body.logoAziendale());
        clienteRepository.save(newCliente);
        Indirizzo indirizzo = new Indirizzo(body.comune(), body.cap(), body.località(), body.civico(), body.via());
        indirizzo.setIndirizzoCliente(clienteRepository.findById(newCliente.getId()).orElseThrow(() -> new NotFoundException(newCliente.getId())));
        indirizzoRepository.save(indirizzo);
        newCliente.addSede(indirizzo);
        clienteRepository.save(newCliente);
        return newCliente;
    }
}
