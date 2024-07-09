package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.StatoFattura;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.payloads.fatture.NuovaStatoFatturaDTO;
import giovanni.epicenergy.repositories.fatture.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatoFatturaService {
    @Autowired
    private StatoFatturaRepository statoFatturaRepository;

    public StatoFattura save(NuovaStatoFatturaDTO body){
        if (statoFatturaRepository.findByStato(body.stato()).isPresent()){
            throw new BadRequestException("Stato fattura già inserito!");
        }else {

            return statoFatturaRepository.save(new StatoFattura(body.stato()));
        }
    }
}
