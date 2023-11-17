package angelomoreno.progetto_diciannovesima_settimana.controllers;

import angelomoreno.progetto_diciannovesima_settimana.entities.Utente;
import angelomoreno.progetto_diciannovesima_settimana.exceptions.BadRequestException;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.UtenteDTO;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.UtenteLoginDTO;
import angelomoreno.progetto_diciannovesima_settimana.payloads.entities.UtenteLoginSuccessDTO;
import angelomoreno.progetto_diciannovesima_settimana.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UtenteLoginSuccessDTO login(@RequestBody UtenteLoginDTO body) {
        return new UtenteLoginSuccessDTO(authService.authenticateUtente(body));
    }

    @PostMapping("/register")
    public Utente saveUtente(@RequestBody @Validated UtenteDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            try {
                return authService.saveUtente(body);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
