package giovanni.epicenergy.payloads;

import giovanni.epicenergy.enums.TipoUtenteENUM;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NuovoUtenteDTO(
        TipoUtenteENUM ruolo,
        @NotEmpty(message = "Lo username è obbligatorio!")
        @Size(min = 3, max = 25, message = "La password deve essere compresa tra 3 e 25 caratteri!")
        String userName,
        @NotEmpty(message = "Lo username è obbligatorio!")
        String email,
        @NotEmpty(message = "Lo username è obbligatorio!")
        String password,
        @NotEmpty(message = "Lo username è obbligatorio!")
        String nome,
        @NotEmpty(message = "Lo username è obbligatorio!")
        String cognome
) {
}
