package giovanni.epicenergy.payloads.clienti;

import giovanni.epicenergy.enums.RagioneSocialeENUM;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NuovoClienteDTO(
        @NotEmpty
        RagioneSocialeENUM ragioneSociale,
        @NotNull
        @Size(min = 11, max = 11, message = "La partita iva deve essere di 11 cifre")
        long partitaIva,
        @NotEmpty
        String email,
        LocalDate dataInserimento,
        LocalDate dataUltimoContatto,
        @NotNull
        long fatturatoAnnuale,
        @NotEmpty
        String pec,
        @NotEmpty
        String emailContatto,
        @NotEmpty
        String nomeContatto,
        @NotNull
        @Size(min = 10, max = 10, message = "Il numero di telefono deve contenere 10 cifre")
        long telefonoContatto,
        String logoAziendale,
        @NotEmpty
        String via,
        @NotEmpty
        String civico,
        @NotEmpty
        String cap,
        @NotEmpty
        String comune
        ) {
}
