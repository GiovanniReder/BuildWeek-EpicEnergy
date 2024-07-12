package giovanni.epicenergy.payloads.filtri;

import jakarta.validation.constraints.NotNull;

public record FatturatoDTO(
        @NotNull(message = "Numero 1 obbligatoria")
        long rangeOne ,
        @NotNull(message = "Numero 2 obbligatoria")
        long rangeTwo) {
}
