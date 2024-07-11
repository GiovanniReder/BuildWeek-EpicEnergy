package giovanni.epicenergy.payloads.fatture;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record NuovaFatturaDTO(
        @NotNull
        @Size(min = 10, message = "Il numero deve contenere un minimo di 10 Numeri")
        long numero,
        LocalDate data ,
        @NotEmpty
        @NotNull
        long importo,
        @NotNull
        UUID clienteId
) {
}
