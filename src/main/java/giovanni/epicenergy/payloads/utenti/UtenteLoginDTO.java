package giovanni.epicenergy.payloads.utenti;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UtenteLoginDTO(
        @NotEmpty(message = "L'email è obbligatoria!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email non valida")
        String email,
        @NotEmpty(message = "La password è obbligatoria!")
        @Size(min = 3, max = 25, message = "La password deve essere compresa tra 3 e 25 caratteri!")
        String password) {
}
