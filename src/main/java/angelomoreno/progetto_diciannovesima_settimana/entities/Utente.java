package angelomoreno.progetto_diciannovesima_settimana.entities;

import angelomoreno.progetto_diciannovesima_settimana.enums.TipoUtente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "persone")
@Getter
@Setter
@JsonIgnoreProperties({"password"})
public class Utente implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "persona_id")
    private UUID personaId;
    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private int eta;
    @Column(name = "data_di_nascita")
    private LocalDate dataDiNascita;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_utente")
    private TipoUtente tipoUtente;
    @OneToMany(mappedBy = "utente")
    private List<Biglietto> biglietti;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.tipoUtente.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
