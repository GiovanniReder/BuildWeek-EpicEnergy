package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Comune;
import giovanni.epicenergy.repositories.comuni_e_province.ComuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComuneService {
    @Autowired
    private ComuneRepository comuneRepository;

//    public List<Comune> getAllComuniByProvincia(String provincia){
//        return comuneRepository.findByProvincia(provincia);
//    }
}
