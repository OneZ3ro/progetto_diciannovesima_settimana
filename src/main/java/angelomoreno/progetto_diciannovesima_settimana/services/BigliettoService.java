package angelomoreno.progetto_diciannovesima_settimana.services;

import angelomoreno.progetto_diciannovesima_settimana.entities.Biglietto;
import angelomoreno.progetto_diciannovesima_settimana.exceptions.NotFoundException;
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

    public Page<Biglietto> getBiglietti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return bigliettoRepository.findAll(pageable);
    }

    public Biglietto findById(UUID id) throws NotFoundException {
        return bigliettoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<Biglietto> bigliettiDisponibili(UUID eventoID) {
        return bigliettoRepository.findByBigliettiDisponibili(eventoID).stream().filter(Biglietto::isDisponibile).toList();
    }

    public void eliminaBiglietto(UUID id) throws NotFoundException {
        bigliettoRepository.deleteById(id);
    }
}
