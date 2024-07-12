package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Cliente;
import giovanni.epicenergy.entities.Comune;
import giovanni.epicenergy.entities.Fattura;
import giovanni.epicenergy.entities.Indirizzo;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.payloads.NuovoIndirizzoDTO;
import giovanni.epicenergy.payloads.clienti.ClienteResponseDTO;
import giovanni.epicenergy.payloads.clienti.NuovoClienteDTO;
import giovanni.epicenergy.payloads.clienti.NuovoClienteResponseDTO;
import giovanni.epicenergy.payloads.clienti.NuovoIndirizzoResponseDTO;
import giovanni.epicenergy.payloads.filtri.ClientePerNomeDTO;
import giovanni.epicenergy.payloads.filtri.DataInserimentoDTO;
import giovanni.epicenergy.payloads.filtri.DataUltimoContattoDTO;
import giovanni.epicenergy.payloads.filtri.FatturatoDTO;
import giovanni.epicenergy.repositories.ClienteRepository;
import giovanni.epicenergy.repositories.IndirizzoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        if (clienteRepository.findByNomeContattoOrEmailContattoOrTelefonoContatto(body.nomeContatto(), body.emailContatto(), body.telefonoContatto()).isPresent()){
            throw new BadRequestException("Email / telefono / nome contatto già presente!");
        }
        Cliente newCliente = new Cliente(body.ragioneSociale(), body.partitaIva(), body.email(), body.dataInserimento(),
                body.dataUltimoContatto(), body.fatturatoAnnuale(), body.pec(), body.emailContatto(), body.nomeContatto(),
                body.telefonoContatto(), body.logoAziendale());
        clienteRepository.save(newCliente);
        Indirizzo indirizzo = new Indirizzo(body.comune(), body.cap(), body.civico(), body.via());
        Comune comune = comuneService.findComuneByCapAndName(String.valueOf(body.cap()), body.comune());
        indirizzo.setLocalità(comune.getProvincia().getSigla() + ", " + indirizzo.getComune() + ", " + indirizzo.getCap());
        indirizzo.setIndirizzoCliente(newCliente);
        indirizzoRepository.save(indirizzo);
        newCliente.addSede(indirizzo);
        return clienteRepository.save(newCliente);
    }

    public Cliente findById(UUID clienteId){
        return clienteRepository.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
    }

    public Page<ClienteResponseDTO> getAll(int pageNumber, int pageSize, String sortBy){
        if(pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.clienteRepository.findAll(pageable).map((cliente) -> new ClienteResponseDTO(
                cliente.getRagioneSociale(),
                cliente.getPartitaIva(),
                cliente.getEmail(),
                cliente.getDataInserimento(),
                cliente.getDataUltimoContatto(),
                cliente.getFatturatoAnnuale(),
                cliente.getPec(),
                cliente.getEmailContatto(),
                cliente.getNomeContatto(),
                cliente.getTelefonoContatto(),
                cliente.getLogoAziendale(),
                cliente.getSedi().getFirst().getVia(),
                cliente.getSedi().getFirst().getCivico(),
                cliente.getSedi().getFirst().getCap(),
                cliente.getSedi().getFirst().getComune(),
                cliente.getSedi().getFirst().getLocalità()));
    }

    public Indirizzo addIndirizzo(UUID clienteId, NuovoIndirizzoDTO body){
        Cliente cliente = this.findById(clienteId);
        if (cliente.getSedi().size() > 1){
            throw new BadRequestException("Si può avere massimo due indirizzi");
        }
        Indirizzo indirizzo = new Indirizzo(body.comune(), body.cap(), body.civico(), body.via());
        Comune comune = comuneService.findComuneByCapAndName(String.valueOf(body.cap()), body.comune());
        indirizzo.setLocalità(body.cap() + ", " + body.comune() + ", " + comune.getProvincia().getSigla());
        indirizzo.setIndirizzoCliente(cliente);
        indirizzoRepository.save(indirizzo);
        cliente.addSede(indirizzo);
        clienteRepository.save(cliente);
        return indirizzo;
    }

    public Indirizzo updateIndirizzo( UUID indirizzoId, NuovoIndirizzoDTO body){
        Indirizzo indirizzo = indirizzoRepository.findById(indirizzoId).orElseThrow(() -> new NotFoundException(indirizzoId) );
        indirizzo.setVia(body.via());
        indirizzo.setCap(body.cap());
        indirizzo.setComune(body.comune());
        indirizzo.setCivico(body.civico());
        Comune comune = comuneService.findComuneByCapAndName(String.valueOf(body.cap()), body.comune());
        indirizzo.setLocalità(body.cap() + ", " + body.comune() + ", " + comune.getProvincia().getSigla());
        indirizzoRepository.save(indirizzo);
        clienteRepository.save(indirizzo.getIndirizzoCliente());
        return indirizzo;
    }

    public ClienteResponseDTO updateCliente(UUID clienteId , NuovoClienteDTO body){
        Cliente found= this.findById(clienteId);
        found.setRagioneSociale(body.ragioneSociale());
        found.setPartitaIva(body.partitaIva());
        found.setEmail(body.email());
        found.setDataInserimento(body.dataInserimento());
        found.setDataUltimoContatto(body.dataUltimoContatto());
        found.setFatturatoAnnuale(body.fatturatoAnnuale());
        found.setPec(body.pec());
        found.setEmailContatto(body.emailContatto());
        found.setNomeContatto(body.nomeContatto());
        found.setTelefonoContatto(body.telefonoContatto());
        found.setLogoAziendale(body.logoAziendale());
        clienteRepository.save(found);
        return new ClienteResponseDTO(
               found.getRagioneSociale(),
               found.getPartitaIva(),
               found.getEmail(),
               found.getDataInserimento(),
               found.getDataUltimoContatto(),
               found.getFatturatoAnnuale(),
               found.getPec(),
               found.getEmailContatto(),
               found.getNomeContatto(),
               found.getTelefonoContatto(),
               found.getLogoAziendale(),
               found.getSedi().getFirst().getVia(),
               found.getSedi().getFirst().getCivico(),
               found.getSedi().getFirst().getCap(),
               found.getSedi().getFirst().getComune(),
               found.getSedi().getFirst().getLocalità());
    }

    public void deleteIndirizzo( UUID indirizzoId){
        Indirizzo indirizzo = indirizzoRepository.findById(indirizzoId).orElseThrow(() -> new NotFoundException(indirizzoId) );
        indirizzoRepository.delete(indirizzo);
        clienteRepository.save(indirizzo.getIndirizzoCliente());
    }

    public void deleteCliente(UUID clienteId){
        Cliente deleteCliente = this.findById(clienteId);
        for (int i = 0; i < deleteCliente.getSedi().size(); i++) {
            indirizzoRepository.delete(deleteCliente.getSedi().getFirst());
        }
        clienteRepository.delete(deleteCliente);
    }

    public Page<Cliente> filterByFatturatoAnnuale(FatturatoDTO range, int pageNumber, int pageSize, String sortBy) {
        if(pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return clienteRepository.filterByFatturato(range.rangeOne(), range.rangeTwo(), pageable);
    }

    public Page<Cliente> filterByDataInserimento(DataInserimentoDTO range, int pageNumber, int pageSize, String sortBy) {
        if(pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return clienteRepository.filterByDataInserimento(range.dataOne(), range.dataTwo(), pageable);
    }

    public Page<Cliente> filterByDataUltimoContatto(DataUltimoContattoDTO range , int pageNumber, int pageSize, String sortBy){
        if(pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return clienteRepository.filterByDataUltimoContatto(range.dataOne(), range.dataTwo(), pageable);
    }

    public Page<Cliente> filterByNomeContatto(ClientePerNomeDTO name , int pageNumber, int pageSize, String sortBy){
        if(pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return clienteRepository.filterByNomeContatto(name.partialName(), pageable);
    }

    //SERVICE DELLE FATTURE
    public Page<Fattura> filterFattureByCliente(UUID clienteId, int pageNumber, int pageSize, String sortBy){
        if(pageSize > 20) pageSize = 20;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return clienteRepository.filterFattureByCliente(clienteId, pageable);
    }
}
