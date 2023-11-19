package angelomoreno.progetto_diciannovesima_settimana.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CompraBigliettoDTO(
        @NotNull(message = "Devi mettere una quantità di biglietti che vuoi comprare")
        int totBiglietti,
        @NotNull(message = "Devi passare l'id dell'utente")
        UUID utenteId,
        @NotNull(message = "Devi passare l'id dell'evento")
        UUID eventoId
) {
}
