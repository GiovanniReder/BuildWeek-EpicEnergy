package giovanni.epicenergy.payloads.fatture;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record NuovaFatturaDTO(

        @NotNull(message = "Numero fattura obbligatorio")
        long numero,

        @NotNull(message = "Data della fattura obbligatoria")
        LocalDate data ,

        @NotNull(message = "Importo fattura obbligatorio")
        long importo,

        @NotEmpty(message = "Id associato al cliente obbligatorio")
        UUID clienteId
) {
}
