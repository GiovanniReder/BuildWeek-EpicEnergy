package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.RuoloUtente;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.payloads.NuovoRuoloDTO;
import giovanni.epicenergy.repositories.RuoloUtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuoloUtenteService {
    @Autowired
    private RuoloUtenteRepository ruoloUtenteRepository;

    public RuoloUtente save(NuovoRuoloDTO body){
        if (
                ruoloUtenteRepository.findByRuolo(body.ruolo()).isPresent()
        ) {
            throw new BadRequestException("Ruolo già esistente");
        } else {

            return     this.ruoloUtenteRepository.save(new RuoloUtente(body.ruolo()));
        }
    }







}
