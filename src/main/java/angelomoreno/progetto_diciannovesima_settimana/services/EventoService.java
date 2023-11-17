package angelomoreno.progetto_diciannovesima_settimana.services;

import angelomoreno.progetto_diciannovesima_settimana.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
}
