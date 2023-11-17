package angelomoreno.progetto_diciannovesima_settimana.services;

import angelomoreno.progetto_diciannovesima_settimana.entities.Evento;
import angelomoreno.progetto_diciannovesima_settimana.exceptions.NotFoundException;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.EventoDTO;
import angelomoreno.progetto_diciannovesima_settimana.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    public Page<Evento> getEventi(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return eventoRepository.findAll(pageable);
    }

    public Evento findById(UUID id) throws NotFoundException {
        return eventoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Evento findByTitolo(String titolo) throws NotFoundException {
        return eventoRepository.findByTitolo(titolo).orElseThrow(() -> new NotFoundException(titolo));
    }

    public Evento findByData(LocalDate data) throws NotFoundException {
        return eventoRepository.findByData(data).orElseThrow(() -> new NotFoundException(data));
    }

    public Evento findByLuogo(String luogo) throws NotFoundException {
        return eventoRepository.findByLuogo(luogo).orElseThrow(() -> new NotFoundException(luogo));
    }

    public Evento saveEvento(EventoDTO body) {
        Evento evento = new Evento();
        evento.setTitolo(body.titolo());
        evento.setDescrizione(body.descrizione());
        evento.setImg(body.img());
        evento.setData(body.data());
        evento.setLuogo(body.luogo());
        evento.setPostiDisponibili(body.postiDisponibili());
        return eventoRepository.save(evento);
    }

    public Evento modificaEvento(UUID id, Evento body) throws NotFoundException {
        Evento evento = this.findById(id);
        evento.setTitolo(body.getTitolo());
        evento.setDescrizione(body.getDescrizione());
        evento.setData(body.getData());
        evento.setLuogo(body.getLuogo());
        evento.setImg(body.getImg());
        return eventoRepository.save(evento);
    }

    public void eliminaEvento(UUID id) throws NotFoundException {
        eventoRepository.deleteById(id);
    }
}
