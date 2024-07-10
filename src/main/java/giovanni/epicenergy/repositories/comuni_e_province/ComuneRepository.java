package giovanni.epicenergy.repositories.comuni_e_province;

import giovanni.epicenergy.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ComuneRepository extends JpaRepository<Comune, UUID> {
   // List<Comune> findByProvincia(String provincia);
}
