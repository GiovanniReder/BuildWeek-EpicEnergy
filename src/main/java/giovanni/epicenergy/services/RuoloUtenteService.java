package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.RuoloUtente;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.payloads.ruoli.NuovoRuoloDTO;
import giovanni.epicenergy.repositories.utente.RuoloUtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuoloUtenteService {
    @Autowired
    private RuoloUtenteRepository ruoloUtenteRepository;

    public RuoloUtente save(NuovoRuoloDTO body){
        if (ruoloUtenteRepository.findByRuolo(body.ruolo()).isPresent()) {
            throw new BadRequestException("Ruolo già esistente");
        } else {
            return this.ruoloUtenteRepository.save(new RuoloUtente(body.ruolo()));
        }
    }

    public RuoloUtente findByRuolo(String ruolo){
        return this.ruoloUtenteRepository.findByRuolo(ruolo).orElseThrow(()-> new NotFoundException("Ruolo non trovato!"));
    }

}
