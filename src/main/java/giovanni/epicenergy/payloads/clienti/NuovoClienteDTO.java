package giovanni.epicenergy.payloads.clienti;

import giovanni.epicenergy.enums.RagioneSocialeENUM;

import java.time.LocalDate;

public record NuovoClienteDTO(
        RagioneSocialeENUM ragioneSociale,
        long partitaIva,
        String email,
        LocalDate dataInserimento,
        LocalDate dataUltimoContatto,
        long fatturatoAnnuale,
        String pec,
        String emailContatto,
        String nomeContatto,
        long telefonoContatto,
        String logoAziendale,
        String via,
        long civico,
        String località,
        long cap,
        String comune
        ) {
}
