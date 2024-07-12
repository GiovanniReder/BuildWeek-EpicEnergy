package giovanni.epicenergy.repositories.utente;

import giovanni.epicenergy.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UtenteRepository extends JpaRepository<Utente , UUID> {
    Optional<Utente> findByEmail(String email);

    @Query("SELECT u FROM Utente u JOIN FETCH u.ruoli WHERE u.id = :id")
    Optional<Utente> findByIdWithRuoli(@Param("id") Long id);

    Optional<Utente> findByEmailOrUserName(String email, String userName);
}
