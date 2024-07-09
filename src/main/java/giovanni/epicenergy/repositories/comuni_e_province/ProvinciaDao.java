package giovanni.epicenergy.repositories.comuni_e_province;

import giovanni.epicenergy.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProvinciaDao extends JpaRepository<Provincia, UUID> {
}
