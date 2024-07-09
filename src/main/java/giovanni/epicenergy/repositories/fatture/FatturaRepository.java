package giovanni.epicenergy.repositories.fatture;

import giovanni.epicenergy.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface FatturaRepository extends JpaRepository<Fattura , UUID> {
    Optional<Fattura>findByNumero(long numero);

}
