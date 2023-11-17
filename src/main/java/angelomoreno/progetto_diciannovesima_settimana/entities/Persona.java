package angelomoreno.progetto_diciannovesima_settimana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "persone")
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"password"})
public class Persona {
    @Id
    @GeneratedValue
    @Column(name = "persona_id")
    private UUID personaId;
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private int eta;
    @Column(name = "data_di_nascita")
    private LocalDate dataDiNascita;

    public Persona(String username, String password, String nome, String cognome, int eta, LocalDate dataDiNascita) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.dataDiNascita = dataDiNascita;
    }
}
