package giovanni.epicenergy.payloads;

import jakarta.validation.constraints.NotEmpty;

public record NuovoIndirizzoDTO(
        @NotEmpty(message = "Via obbligatoria ")
        String via,
        @NotEmpty(message = "Civico obbligatorio ")
        String civico,
        @NotEmpty(message = "Cap obbligatorio ")
        String cap,
        @NotEmpty(message = "Comune obbligatorio ")
        String comune) {
}
