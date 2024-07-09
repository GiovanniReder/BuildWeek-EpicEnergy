package giovanni.epicenergy.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giovanni.epicenergy.entities.Utente;
import giovanni.epicenergy.enums.TipoUtenteENUM;
import giovanni.epicenergy.exceptions.BadRequestException;
import giovanni.epicenergy.exceptions.NotFoundException;
import giovanni.epicenergy.payloads.NuovoUtenteDTO;
import giovanni.epicenergy.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private Cloudinary cloudinary;

    public Utente save(NuovoUtenteDTO body) {
        this.utenteRepository.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("email già in uso");
                }
        );

        Utente newUser = new Utente( body.cognome(), body.nome(), bcrypt.encode(body.password()), body.email(), body.userName());
        return utenteRepository.save(newUser);
    }

    public Utente findById(UUID utenteId){
        return utenteRepository.findById(utenteId).orElseThrow(()-> new NotFoundException(utenteId));
    }
    public Utente findByMail(String email){
        return utenteRepository.findByEmail(email).orElseThrow(()-> new NotFoundException("Utente con mail: " + email + " non trovato!"));
    }

    public String uploadAvatar(MultipartFile file) throws IOException {
        return (String) this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }

    public Utente patchAvatarUtente(Utente utente , String url ){
        utente.setAvatar(url);
        return utenteRepository.save(utente);
    }
}





