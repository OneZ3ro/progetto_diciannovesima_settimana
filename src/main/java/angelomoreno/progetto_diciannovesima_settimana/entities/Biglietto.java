package angelomoreno.progetto_diciannovesima_settimana.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "biglietti")
@Getter
@Setter
public class Biglietto {
    @Id
    @GeneratedValue
    @Column(name = "biglietto_id")
    private UUID bigliettoId;
    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;
}
