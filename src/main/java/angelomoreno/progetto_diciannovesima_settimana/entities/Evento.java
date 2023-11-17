package angelomoreno.progetto_diciannovesima_settimana.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "eventi")
@Getter
@Setter
public class Evento {
    @Id
    @GeneratedValue
    @Column(name = "evento_id")
    private UUID eventoId;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String luogo;
    @Column(name = "posti_disponibili")
    private long postiDisponibili;
    @OneToMany(mappedBy = "evento")
    private List<Biglietto> biglietti;
}
