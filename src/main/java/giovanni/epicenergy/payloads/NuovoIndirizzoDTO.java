package giovanni.epicenergy.payloads;

public record NuovoIndirizzoDTO(
        String via,
        String civico,
        String cap,
        String comune) {
}
