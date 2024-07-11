package giovanni.epicenergy;

import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.payloads.ruoli.NuovoRuoloDTO;
import giovanni.epicenergy.services.RuoloUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    private RuoloUtenteService ruoloUtenteService;

    @Override
    public void run(String... args) throws Exception {
        try {
           ruoloUtenteService.save(new NuovoRuoloDTO("USER") );
           ruoloUtenteService.save(new NuovoRuoloDTO("ADMIN") );
        } catch(BadRequestException error){
            error.printStackTrace();
       }
    }
}
