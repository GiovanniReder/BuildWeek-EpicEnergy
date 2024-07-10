package giovanni.epicenergy.payloads.fatture;

import java.time.LocalDate;
import java.util.UUID;

public record NuovaFatturaDTO(long numero, LocalDate data , long importo, UUID clienteId) {
}
