package giovanni.epicenergy.repositories;

import giovanni.epicenergy.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComuneDao extends JpaRepository<Comune, UUID> {
}
