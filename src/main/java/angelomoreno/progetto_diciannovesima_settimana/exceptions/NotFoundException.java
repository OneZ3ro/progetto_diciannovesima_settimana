package angelomoreno.progetto_diciannovesima_settimana.exceptions;

import java.time.LocalDate;
import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id) {
        super("L'elemento con id: " + id + " non è stato trovato. Riprovare con un id diverso");
    }
    public NotFoundException(String email) {
        super("L'elemento con email: " + email + " non è stato trovato. Riprovare con una email diversa");
    }

    public NotFoundException(LocalDate data) {
        super("L'elemento in data: " + data + " non corrisponde a nulla. Riprova con un'altra data");
    }
}
