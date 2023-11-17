package angelomoreno.progetto_diciannovesima_settimana.services;

import angelomoreno.progetto_diciannovesima_settimana.entities.Utente;
import angelomoreno.progetto_diciannovesima_settimana.enums.TipoUtente;
import angelomoreno.progetto_diciannovesima_settimana.exceptions.BadRequestException;
import angelomoreno.progetto_diciannovesima_settimana.exceptions.UnauthorizedException;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.UtenteDTO;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.UtenteLoginDTO;
import angelomoreno.progetto_diciannovesima_settimana.repositories.UtenteRepository;
import angelomoreno.progetto_diciannovesima_settimana.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class AuthService {
    @Autowired
    private UtenteService utenteService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUtente(UtenteLoginDTO body) {
        Utente utente = utenteService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Le credenziali non sono valide. Riprova");
        }
    }

    public Utente saveUtente(UtenteDTO body) throws IOException {
        utenteRepository.findByEmail(body.email()).ifPresent(utente -> {
            throw new BadRequestException("L'email " + body.email() + " è già stata utilizzata!");
        });
        Utente utente = new Utente();
        utente.setUsername(body.username());
        utente.setEmail(body.email());
        utente.setPassword(bcrypt.encode(body.password()));
        utente.setNome(body.nome());
        utente.setCognome(body.cognome());
        utente.setDataDiNascita(body.dataDiNascita());
        utente.setEta(LocalDate.now().getYear() - body.dataDiNascita().getYear());
        utente.setTipoUtente(TipoUtente.UTENTE);
        return utenteRepository.save(utente);
    }
}
