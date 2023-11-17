package angelomoreno.progetto_diciannovesima_settimana.controllers;

import angelomoreno.progetto_diciannovesima_settimana.entities.Evento;
import angelomoreno.progetto_diciannovesima_settimana.exceptions.BadRequestException;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.EventoDTO;
import angelomoreno.progetto_diciannovesima_settimana.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @GetMapping("")
    public Page<Evento> getUtenti (@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   @RequestParam(defaultValue = "eventoId") String orderBy) {
        return eventoService.getEventi(page, size, orderBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Evento creaEvento(@RequestBody @Validated EventoDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return eventoService.saveEvento(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

    @PostMapping("/{id}/upload")
    public String uploadFiles(@PathVariable UUID id, @RequestParam("img")MultipartFile body) throws IOException{
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return eventoService.uploadPicture(id, body);
    }
}
