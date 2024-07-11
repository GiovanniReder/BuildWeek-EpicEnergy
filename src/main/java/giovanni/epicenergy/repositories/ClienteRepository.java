package giovanni.epicenergy.repositories;

import giovanni.epicenergy.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

        @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :rangeOne AND :rangeTwo")
        Page<Cliente> filterByFatturato( long rangeOne,
                                         long rangeTwo,
                                        Pageable pageable);
    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento BETWEEN :dateOne AND :dateTwo")
    Page<Cliente> filterByDataInserimento( LocalDate dateOne,
                                     LocalDate dateTwo,
                                     Pageable pageable);


}
