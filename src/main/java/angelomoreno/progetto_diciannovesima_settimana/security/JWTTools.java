package angelomoreno.progetto_diciannovesima_settimana.security;

import angelomoreno.progetto_diciannovesima_settimana.entities.Utente;
import angelomoreno.progetto_diciannovesima_settimana.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${spring.jwt.secret}")
    private String segreto;

    public String createToken(Utente utente) {
        return Jwts.builder().setSubject(String.valueOf(utente.getUtenteId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)))
                .signWith(Keys.hmacShaKeyFor(segreto.getBytes()))
                .compact();
    }

    public void verifyToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(segreto.getBytes())).build().parse(token);
        } catch (Exception exception) {
            throw new UnauthorizedException("Il token non è valido. Fai il login per un nuovo token")
        }
    }

    public String extractIdFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(segreto.getBytes())).build().parseClaimsJws(token).getBody().getSubject();
    }
}
