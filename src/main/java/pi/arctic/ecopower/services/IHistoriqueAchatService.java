package pi.arctic.ecopower.services;

import pi.arctic.ecopower.entities.Historique;

import java.util.List;
import java.util.Optional;

public interface IHistoriqueAchatService {

    void add(Historique h);
    Historique update(Historique H);
    List<Historique> getAll();
    Optional<Historique> getById(int id);
    void remove(int id);

}
