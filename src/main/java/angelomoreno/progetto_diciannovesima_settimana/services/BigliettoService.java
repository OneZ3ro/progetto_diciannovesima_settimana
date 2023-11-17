package angelomoreno.progetto_diciannovesima_settimana.services;

import angelomoreno.progetto_diciannovesima_settimana.repositories.BigliettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BigliettoService {
    @Autowired
    private BigliettoRepository bigliettoRepository;
}
