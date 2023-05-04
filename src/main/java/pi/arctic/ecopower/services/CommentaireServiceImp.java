package pi.arctic.ecopower.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.entities.Commentaire;
import pi.arctic.ecopower.entities.Product;
import pi.arctic.ecopower.repositories.CommentaireRepo;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CommentaireServiceImp implements ICommentaireService {
    private CommentaireRepo commentaireRepo;
    @Override
    public void add(Commentaire c) {
        commentaireRepo.save(c);

    }

    @Override
    public Commentaire update(Commentaire c) {
        return  commentaireRepo.save(c);
    }

    @Override
    public List<Commentaire> getAll() {
        return commentaireRepo.findAll();
    }

    @Override
    public Commentaire getById(long id) {
        return commentaireRepo.findById(id).orElse(null);
    }

    @Override
    public void remove(long id) {
        commentaireRepo.deleteById(id);
    }

    public Set<Commentaire> getCommentsForProduct(Product product){
        return commentaireRepo.findByProductCommentaire(product);
    }
}
