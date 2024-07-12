package giovanni.epicenergy.payloads.filtri;

import jakarta.validation.constraints.NotEmpty;

public record ClientePerNomeDTO(
        @NotEmpty(message = "Nome obbligatorio")
        String partialName) {
}
