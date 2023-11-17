package angelomoreno.progetto_diciannovesima_settimana.controllers;

import angelomoreno.progetto_diciannovesima_settimana.entities.Biglietto;
import angelomoreno.progetto_diciannovesima_settimana.entities.Utente;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.CompraBigliettoDTO;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.CompraBigliettoSuccessDTO;
import angelomoreno.progetto_diciannovesima_settimana.services.BigliettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/biglietti")
public class BigliettoController {
    @Autowired
    private BigliettoService bigliettoService;

    @GetMapping("")
    public Page<Biglietto> getBiglietti (@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "bigliettoId") String orderBy) {
        return bigliettoService.getBiglietti(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Biglietto getBigliettoById(@PathVariable UUID id) { return bigliettoService.findById(id); }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN', 'ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaBiglietto(@PathVariable UUID id) {
        bigliettoService.eliminaBiglietto(id);
    }

//    @PostMapping("/compra")
//    public CompraBigliettoSuccessDTO impostaUtenteABiglietti(@RequestBody @Validated CompraBigliettoDTO body) {
//        return new CompraBigliettoSuccessDTO(bigliettoService.impostaUtenteABiglietti(body));
//    }
}
