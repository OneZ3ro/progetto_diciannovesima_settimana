package angelomoreno.progetto_diciannovesima_settimana.payloads.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UtenteDTO(
        @NotEmpty(message = "L'username è obbligatorio")
        @Size(min = 3, max = 16, message = "L'username deve avere tra i 3 e 16 caratteri")
        String username,
        @NotEmpty(message = "La email è obbligatoria")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        String password,
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 30, message = "Il nome deve avere tra i 3 e 30 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 5, max = 50, message = "Il cognome deve avere tra i 5 e 50 caratteri")
        String cognome,
        @NotNull(message = "La data di nascita è obbligatoria")
        LocalDate dataDiNascita
) {}
