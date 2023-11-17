package angelomoreno.progetto_diciannovesima_settimana.services;

import angelomoreno.progetto_diciannovesima_settimana.entities.Biglietto;
import angelomoreno.progetto_diciannovesima_settimana.entities.Evento;
import angelomoreno.progetto_diciannovesima_settimana.entities.Utente;
import angelomoreno.progetto_diciannovesima_settimana.exceptions.NotFoundException;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.CompraBigliettoDTO;
import angelomoreno.progetto_diciannovesima_settimana.repositories.BigliettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BigliettoService {
    @Autowired
    private BigliettoRepository bigliettoRepository;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private UtenteService utenteService;

    public Page<Biglietto> getBiglietti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return bigliettoRepository.findAll(pageable);
    }

    public Biglietto findById(UUID id) throws NotFoundException {
        return bigliettoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void eliminaBiglietto(UUID id) throws NotFoundException {
        bigliettoRepository.deleteById(id);
    }

    public String impostaUtenteABiglietti(CompraBigliettoDTO body) throws NotFoundException {
        Utente utente = utenteService.findById(body.utenteId());
        Evento evento = eventoService.findById(body.eventoId());
        List<Biglietto> bigliettiDisponibili = evento.getBiglietti().stream().filter(Biglietto::isDisponibile).toList();
        for (int i = 0; i < body.totBiglietti(); i++) {
            Biglietto biglietto = bigliettiDisponibili.get(i);
            biglietto.setDisponibile(false);
            biglietto.setUtente(utente);
            bigliettoRepository.save(biglietto);
        }
        return body.totBiglietti() + " biglietti salvati correttamente";
    }
}
