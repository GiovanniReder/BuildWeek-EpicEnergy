package giovanni.epicenergy.payloads.fatture;

import java.time.LocalDate;

public record NuovaFatturaDTO(long numero, LocalDate data , long importo) {
}
