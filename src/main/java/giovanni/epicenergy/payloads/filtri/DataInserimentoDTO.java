package giovanni.epicenergy.payloads.filtri;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DataInserimentoDTO(
        @NotNull(message = "Data 1 obbligatoria")
        LocalDate dataOne ,
        @NotNull(message = "Data 2 obbligatoria")
        LocalDate dataTwo) {
}
