package giovanni.epicenergy.payloads.fatture;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NuovaStatoFatturaDTO(
        @NotEmpty(message = "Stato della fattura obbligatorio")
        String stato) {
}
