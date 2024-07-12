package giovanni.epicenergy.payloads.fatture;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record NuovaFatturaDTO(
       // @NotNull
       // @Size(min = 10, max= 10, message = "Il numero di telefono deve contenere 10 cifre ")
        long numero,
        LocalDate data ,
       // @NotNull
        long importo,
       // @NotEmpty
        UUID clienteId
) {
}
