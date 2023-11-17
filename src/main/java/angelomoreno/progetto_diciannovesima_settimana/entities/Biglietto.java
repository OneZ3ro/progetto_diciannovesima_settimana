package angelomoreno.progetto_diciannovesima_settimana.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "biglietti")
@NoArgsConstructor
@Getter
@Setter
public class Biglietto {
    @Id
    @GeneratedValue
    @Column(name = "biglietto_id")
    private UUID bigliettoId;
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    public Biglietto(UUID bigliettoId, Persona persona, Evento evento) {
        this.bigliettoId = bigliettoId;
        this.persona = persona;
        this.evento = evento;
    }
}
