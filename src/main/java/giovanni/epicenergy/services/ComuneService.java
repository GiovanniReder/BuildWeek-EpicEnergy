package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Comune;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.repositories.comuni_e_province.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComuneService {
    @Autowired
    private ComuneRepository comuneRepository;

    public Comune findComuneByCapAndName(String cap, String name){
        return comuneRepository.findByCapAndNomeComune(cap, name).orElseThrow(()-> new NotFoundException("Comune non trovato"));
    }
//    public List<Comune> getAllComuniByProvincia(String provincia){
//        return comuneRepository.findByProvincia(provincia);
//    }
}
