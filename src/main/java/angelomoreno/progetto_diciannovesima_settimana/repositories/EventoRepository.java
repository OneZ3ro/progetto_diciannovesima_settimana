package angelomoreno.progetto_diciannovesima_settimana.repositories;

import angelomoreno.progetto_diciannovesima_settimana.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
    Optional<Evento> findByTitolo(String titolo);
    Optional<Evento> findByData(LocalDate data);
    Optional<Evento> findByLuogo(String luogo);
}
