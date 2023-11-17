package angelomoreno.progetto_diciannovesima_settimana.services;

import angelomoreno.progetto_diciannovesima_settimana.entities.Biglietto;
import angelomoreno.progetto_diciannovesima_settimana.entities.Evento;
import angelomoreno.progetto_diciannovesima_settimana.entities.Utente;
import angelomoreno.progetto_diciannovesima_settimana.exceptions.NotFoundException;
import angelomoreno.progetto_diciannovesima_settimana.repositories.UtenteRepository;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public Page<Utente> getUtenti(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return utenteRepository.findAll(pageable);
    }

    public Utente findById(UUID id) throws NotFoundException {
        return utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Utente findByEmail(String email) throws NotFoundException{
        return utenteRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    public Utente modificaUtente(UUID id, Utente body) throws NotFoundException {
        Utente utente = this.findById(id);
        utente.setUsername(body.getEmail());
        utente.setEmail(body.getEmail());
        utente.setNome(body.getNome());
        utente.setCognome(body.getCognome());
        utente.setDataDiNascita(body.getDataDiNascita());
        utente.setEta(LocalDate.now().getYear() - utente.getDataDiNascita().getYear());
        return utenteRepository.save(utente);
    }

    public void eliminaUtente(UUID id) throws NotFoundException {
        utenteRepository.deleteById(id);
    }
}
