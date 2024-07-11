package giovanni.epicenergy.repositories.fatture;

import giovanni.epicenergy.entities.Cliente;
import giovanni.epicenergy.entities.Fattura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface FatturaRepository extends JpaRepository<Fattura , UUID> {
    Optional<Fattura>findByNumero(long numero);

    @Query("SELECT f FROM Fattura f WHERE f.stato.stato ILIKE :statoFattura ")
    Page<Fattura> filterByStatoFattura(String statoFattura,
                                    Pageable pageable);
    @Query("SELECT f FROM Fattura f WHERE f.data BETWEEN :dataOne AND :dataTwo   ")
    Page<Fattura> filterByData(LocalDate dataOne, LocalDate dataTwo,
                               Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE f.importo BETWEEN :rangeOne AND :rangeTwo")
    Page<Fattura> filterByImporto( long rangeOne,
                                     long rangeTwo,
                                     Pageable pageable);

    @Query("SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno")
    Page<Fattura> filterByAnno(int anno, Pageable pageable);


}
