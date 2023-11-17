package angelomoreno.progetto_diciannovesima_settimana.services;

import angelomoreno.progetto_diciannovesima_settimana.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

}
