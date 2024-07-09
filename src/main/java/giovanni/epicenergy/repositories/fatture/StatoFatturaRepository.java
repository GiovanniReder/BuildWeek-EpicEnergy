package giovanni.epicenergy.repositories.fatture;

import giovanni.epicenergy.entities.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface StatoFatturaRepository extends JpaRepository<StatoFattura , UUID> {

    Optional<StatoFattura> findByStato(String stato);
}
