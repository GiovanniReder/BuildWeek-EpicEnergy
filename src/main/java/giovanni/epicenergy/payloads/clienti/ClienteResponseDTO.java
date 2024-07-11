package giovanni.epicenergy.payloads.clienti;

import giovanni.epicenergy.enums.RagioneSocialeENUM;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record ClienteResponseDTO(
        RagioneSocialeENUM ragioneSociale,
        long partitaIva,
        String email,
        LocalDate dataInserimento,
        LocalDate dataUltimoContatto,
        long fatturatoAnnuale,
        String pec,
        String emailContatto,
        String nomeContatto,
        String telefonoContatto,
        String logoAziendale,
        String via,
        String civico,
        String cap,
        String comune,
        String provincia
        ) {
}
