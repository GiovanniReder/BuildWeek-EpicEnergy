package giovanni.epicenergy.payloads.ruoli;

import jakarta.validation.constraints.NotEmpty;


public record NuovoRuoloDTO (
        @NotEmpty(message = "Ruolo obbligatorio ")
        String ruolo){
}
