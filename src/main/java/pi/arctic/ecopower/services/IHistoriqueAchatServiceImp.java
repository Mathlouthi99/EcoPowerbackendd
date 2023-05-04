package pi.arctic.ecopower.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.entities.Historique;
import pi.arctic.ecopower.repositories.HistoriqueRepo;

import java.util.List;
import java.util.Optional;

@Service
public class IHistoriqueAchatServiceImp implements IHistoriqueAchatService {
    @Autowired
    HistoriqueRepo historiqueRepo;
   @Override
   public void add(Historique h) {

        historiqueRepo.save(h);   }

    @Override
    public Historique update(Historique H) {
        return historiqueRepo.save(H);
    }

    @Override
    public List<Historique> getAll() {
        return historiqueRepo.findAll();
    }

    @Override
    public Optional<Historique> getById(int id) {
        return historiqueRepo.findById(id);
    }

    @Override
    public void remove(int id) {
    historiqueRepo.deleteById(id);

    }
}
