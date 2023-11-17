package angelomoreno.progetto_diciannovesima_settimana.payloads.entities;

import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record CompraBigliettoDTO(
        @NotEmpty(message = "Devi mettere una quantità di biglietti che vuoi comprare")
        int totBiglietti,
        @NotEmpty(message = "Devi passare l'id dell'utente")
        UUID utenteId,
        @NotEmpty(message = "Devi passare l'id dell'evento")
        UUID eventoId
) {
}
