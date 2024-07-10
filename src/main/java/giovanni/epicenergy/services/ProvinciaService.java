package giovanni.epicenergy.services;

import giovanni.epicenergy.entities.Provincia;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.repositories.comuni_e_province.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    public Provincia findByProvincia(String provincia){
        return provinciaRepository.findByProvincia(provincia).orElseThrow(() -> new NotFoundException("Provincia non trovata!"));
    }
}
