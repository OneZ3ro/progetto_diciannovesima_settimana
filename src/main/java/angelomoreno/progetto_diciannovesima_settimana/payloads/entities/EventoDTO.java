package angelomoreno.progetto_diciannovesima_settimana.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventoDTO(
        @NotEmpty(message = "Il titolo è obbligatorio")
        @Size(min = 5, max = 50, message = "Il titolo deve avere tra i 5 e 50 caratteri")
        String titolo,
        String descrizione,
        String img,
        @NotEmpty(message = "La data è obbligatoria")
        LocalDate data,
        @NotEmpty(message = "Il titolo è obbligatorio")
        @Size(min = 3, max = 30, message = "Il luogo deve avere tra i 3 e 30 caratteri")
        String luogo,
        @NotEmpty(message = "I posti disponibili è obbligatorio")
        long postiDisponibili
) {}
