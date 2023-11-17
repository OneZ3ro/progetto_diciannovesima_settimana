package angelomoreno.progetto_diciannovesima_settimana.repositories;

import angelomoreno.progetto_diciannovesima_settimana.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface EventoRepository extends JpaRepository<Evento, UUID> {
}
