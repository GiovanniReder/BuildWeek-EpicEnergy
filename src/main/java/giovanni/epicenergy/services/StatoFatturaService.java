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
