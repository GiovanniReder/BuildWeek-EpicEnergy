package giovanni.epicenergy.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giovanni.epicenergy.entities.RuoloUtente;
import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.payloads.ruoli.NuovoRuoloDTO;
import giovanni.epicenergy.payloads.ruoli.NuovoRuoloResponseDTO;
import giovanni.epicenergy.payloads.utenti.NuovoUtenteDTO;
import giovanni.epicenergy.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private RuoloUtenteService ruoloUtenteService;


    public Utente save(NuovoUtenteDTO body) {
        this.utenteRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("email già in uso");
                });
        Utente newUser = new Utente(body.cognome(), body.nome(), bcrypt.encode(body.password()), body.email(),
                body.userName());
        newUser.setRuoli(new ArrayList<>(Arrays.asList(ruoloUtenteService.findByRuolo("USER"))));
        return utenteRepository.save(newUser);
    }

    public Utente findById(UUID utenteId) {
        return utenteRepository.findById(utenteId).orElseThrow(() -> new NotFoundException(utenteId));
    }

    public Utente findByMail(String email) {
        return utenteRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente con mail: " + email + " non trovato!"));
    }

    public String uploadAvatar(MultipartFile file) throws IOException {
        return (String) this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }

    public Utente patchAvatarUtente(Utente utente, String url) {
        utente.setAvatar(url);
        return utenteRepository.save(utente);
    }

    public Utente patchRuolo(NuovoRuoloResponseDTO body , UUID utenteId){
      Utente found= utenteRepository.findById(utenteId).orElseThrow(()-> new NotFoundException(utenteId));
        List<RuoloUtente> ruoli = new ArrayList<>(found.getRuoli());

        ruoli.add(ruoloUtenteService.findByRuolo(body.ruolo()));
          found.setRuoli(ruoli);
          return utenteRepository.save(found);

    }
}
