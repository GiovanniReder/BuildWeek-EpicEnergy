package giovanni.epicenergy.controllers;

import giovanni.epicenergy.services.RuoloUtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ruoli")
public class RuoloController {

    @Autowired
    private RuoloUtenteService ruoloUtenteService;

    // save che imposta un admin



}
