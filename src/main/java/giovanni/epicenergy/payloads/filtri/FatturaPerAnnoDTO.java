package giovanni.epicenergy.payloads.filtri;

import jakarta.validation.constraints.NotNull;

public record FatturaPerAnnoDTO(
        @NotNull(message = "Anno obbligatorio")
        int anno) {
}
