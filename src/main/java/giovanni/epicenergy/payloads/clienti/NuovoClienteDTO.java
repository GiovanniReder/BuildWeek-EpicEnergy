package giovanni.epicenergy.payloads.clienti;

import giovanni.epicenergy.enums.RagioneSocialeENUM;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record NuovoClienteDTO(
        RagioneSocialeENUM ragioneSociale,
        @NotNull(message = "Partiva iva obbligatoria")
        //@Max(11)
        //@Size(min = 4, max = 4, message = "La partita iva deve essere di 11 cifre")
        long partitaIva,
        @NotEmpty(message = "Email obbligatoria")
        String email,
        @NotNull(message = "Data inserimento obbligatoria")
        LocalDate dataInserimento,
        @NotNull(message = "Data ultimo contatto obbligatoria")
        LocalDate dataUltimoContatto,
        @NotNull(message = "Fatturato annuale obbligatorio")
        long fatturatoAnnuale,
        @NotEmpty(message = "Pec obbligatoria")
        String pec,
        @NotEmpty(message = "Email contatto obbligatorio")
        String emailContatto,
        @NotEmpty(message = "Nome contatto obbligatoria")
        String nomeContatto,
        @NotEmpty(message = "Telefono contatto obbligatorio")
        @Pattern(regexp = "^\\s*(?:\\+?(\\d{1,3}))?([-. (]*(\\d{3})[-. )]*)?((\\d{3})[-. ]*(\\d{2,4})(?:[-.x ]*(\\d+))?)\\s*$", message = "Formato telefono non valido")
        String telefonoContatto,
        String logoAziendale,
        @NotEmpty(message = "Via obbligatoria")
        String via,
        @NotEmpty(message = "Civico obbligatorio")
        String civico,
        @NotEmpty(message = "Cap obbligatoria")
        String cap,
        @NotEmpty(message = "comune obbligatoria")
        String comune
        ) {
}
