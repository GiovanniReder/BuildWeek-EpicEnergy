package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Fattura;
import giovanni.epicenergy.entities.StatoFattura;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.payloads.fatture.NuovaFatturaDTO;
import giovanni.epicenergy.payloads.fatture.NuovaStatoFatturaDTO;
import giovanni.epicenergy.payloads.filtri.DataInserimentoDTO;
import giovanni.epicenergy.payloads.filtri.FatturatoDTO;
import giovanni.epicenergy.repositories.fatture.FatturaRepository;
import giovanni.epicenergy.repositories.fatture.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FatturaService {
    @Autowired
    private FatturaRepository fatturaRepository;
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;
    @Autowired
    private ClienteService clienteService;

    public Fattura save(NuovaFatturaDTO body){
      if (this.fatturaRepository.findByNumero(body.numero()).isPresent()){
          throw new BadRequestException("Fattura già presente");
      }
      Fattura nuova= new Fattura(body.numero(), body.data(), body.importo());
      nuova.setStato(statoFatturaRepository.findByStato("DA_PAGARE").orElseThrow(()-> new BadRequestException("Stato non trovato")));
      nuova.setClienti(clienteService.findById(body.clienteId()));
      return fatturaRepository.save(nuova);
    }

    public Fattura patchStatoFattura(NuovaStatoFatturaDTO body , UUID fatturaid){
        Fattura found = fatturaRepository.findById(fatturaid).orElseThrow(()-> new NotFoundException(fatturaid));
        StatoFattura foundStatoFattura=  statoFatturaRepository.findByStato(body.stato()).orElseThrow(()-> new NotFoundException("Stato fattura non esistente!"));
        found.setStato(foundStatoFattura);
        return fatturaRepository.save(found);
    }

  public Page<Fattura> fatturaPerStato(NuovaStatoFatturaDTO body, int pageNumber, int pageSize, String sortBy){
      if(pageSize > 20) pageSize = 20;
      Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return fatturaRepository.filterByStatoFattura(body.stato(), pageable);
  }

  public Page<Fattura> fatturaPerData(DataInserimentoDTO body, int pageNumber,int pageSize, String sortBy ){
      if(pageSize > 20) pageSize = 20;
      Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
      return fatturaRepository.filterByData(body.dataOne(), body.dataTwo() , pageable);
  }

  public Page<Fattura> fatturaPerImporto(FatturatoDTO body, int pageNumber,int pageSize, String sortBy){
      if(pageSize > 20) pageSize = 20;
      Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
      return fatturaRepository.filterByImporto(body.rangeOne(), body.rangeTwo() , pageable );


  }
}
