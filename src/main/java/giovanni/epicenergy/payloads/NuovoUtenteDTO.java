package giovanni.epicenergy.payloads;

import giovanni.epicenergy.enums.TipoUtenteENUM;

public record NuovoUtenteDTO(

        TipoUtenteENUM ruolo,
        String userName,
        String email,
        String password,
        String nome,
        String cognome



) {
}
