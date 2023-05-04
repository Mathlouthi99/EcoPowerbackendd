package pi.arctic.ecopower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.arctic.ecopower.entities.Historique;

import java.util.Optional;
@Repository
public interface HistoriqueRepo extends JpaRepository<Historique,Integer> {
    Optional<Historique> findById(int id );
}
