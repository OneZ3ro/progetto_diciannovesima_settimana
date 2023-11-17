package angelomoreno.progetto_diciannovesima_settimana.repositories;

import angelomoreno.progetto_diciannovesima_settimana.entities.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface BigliettoRepository extends JpaRepository<Biglietto, UUID> {
    Optional<Biglietto> findByBigliettiDisponibili(UUID eventoId);
}
