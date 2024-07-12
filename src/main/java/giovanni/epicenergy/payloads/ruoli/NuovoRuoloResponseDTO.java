package giovanni.epicenergy.payloads.ruoli;

import jakarta.validation.constraints.NotEmpty;

public record NuovoRuoloResponseDTO(
        @NotEmpty(message = "Ruolo obbligatorio ")
        String ruolo) {
}
