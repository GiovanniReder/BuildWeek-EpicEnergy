package giovanni.epicenergy.repositories;

import giovanni.epicenergy.entities.RuoloUtente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RuoloUtenteRepository extends JpaRepository<RuoloUtente, UUID> {
    Optional<RuoloUtente>  findByRuolo(String ruolo);


}
