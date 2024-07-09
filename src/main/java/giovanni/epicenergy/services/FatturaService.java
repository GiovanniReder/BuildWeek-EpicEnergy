package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Fattura;
import giovanni.epicenergy.entities.StatoFattura;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.payloads.fatture.NuovaFatturaDTO;
import giovanni.epicenergy.payloads.fatture.NuovaStatoFatturaDTO;
import giovanni.epicenergy.repositories.fatture.FatturaRepository;
import giovanni.epicenergy.repositories.fatture.StatoFatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FatturaService {

@Autowired
    private FatturaRepository fatturaRepository;
@Autowired
private StatoFatturaRepository statoFatturaRepository;

public Fattura save(NuovaFatturaDTO body){
  if (this.fatturaRepository.findByNumero(body.numero()).isPresent()){
      throw new BadRequestException("Fattura già presente");
  }
            Fattura nuova= new Fattura(body.numero(), body.data(), body.importo());
  nuova.setStato(statoFatturaRepository.findByStato("DA_PAGARE").orElseThrow(()-> new BadRequestException("Stato non trovato")));
    return
    fatturaRepository.save(nuova);
}



public Fattura patchStatoFattura(NuovaStatoFatturaDTO body , UUID fatturaid){

        Fattura found = fatturaRepository.findById(fatturaid).orElseThrow(()-> new NotFoundException(fatturaid));
     StatoFattura foundStatoFattura=  statoFatturaRepository.findByStato(body.stato()).orElseThrow(()-> new NotFoundException("Stato fattura non esistente!"));
        found.setStato(foundStatoFattura);
        return fatturaRepository.save(found);

}

    /*
    *    public Utente save(NuovoUtenteDTO body) {
        this.utenteRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("email già in uso");
                });
        Utente newUser = new Utente(body.cognome(), body.nome(), bcrypt.encode(body.password()), body.email(),
                body.userName());
        newUser.setRuoli(new ArrayList<>(Arrays.asList(ruoloUtenteService.findByRuolo("USER"))));
        return utenteRepository.save(newUser);
    }
    * */




}
