package angelomoreno.progetto_diciannovesima_settimana.controllers;

import angelomoreno.progetto_diciannovesima_settimana.entities.Evento;
import angelomoreno.progetto_diciannovesima_settimana.entities.Utente;
import angelomoreno.progetto_diciannovesima_settimana.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("")
    public Page<Evento> getUtenti (@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "utenteId") String orderBy) {
        return eventoService.getEventi(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Evento findById(@PathVariable UUID id) {
        return eventoService.findById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE', 'ADMIN')")
    public Evento modificaEvento(@PathVariable UUID id, @RequestBody Evento body) {
        return eventoService.modificaEvento(id, body);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE', 'ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaEvento(@PathVariable UUID id) {
        eventoService.eliminaEvento(id);
    }
}
