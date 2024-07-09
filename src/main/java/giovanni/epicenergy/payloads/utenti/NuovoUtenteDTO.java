package giovanni.epicenergy.payloads.utenti;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NuovoUtenteDTO(
        @NotEmpty(message = "Lo username è obbligatorio!")
        @Size(min = 3, max = 25, message = "Lo username deve essere compreso tra 3 e 25 caratteri!")
        String userName,

        @NotEmpty(message = "L'email è obbligatoria!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email non valida")
        String email,

        @NotEmpty(message = "La password è obbligatoria!")
        @Size(min = 3, max = 25, message = "La password deve essere compresa tra 3 e 25 caratteri!")
        String password,

        @NotEmpty(message = "Il nome è obbligatorio!")
        @Size(min = 3, max = 25, message = "Il nome deve essere compreso tra 3 e 25 caratteri!")
        String nome,

        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 3, max = 25, message = "Il cognome deve essere compreso tra 3 e 25 caratteri!")
        String cognome
) {
}
