package pi.arctic.ecopower.services;

import pi.arctic.ecopower.entities.Commentaire;
import pi.arctic.ecopower.entities.Product;

import java.util.List;
import java.util.Set;

public interface ICommentaireService{
    void add(Commentaire c);
    Commentaire update(Commentaire c);
    List<Commentaire> getAll();
    Commentaire getById(long id);
    void remove(long id);
    Set<Commentaire> getCommentsForProduct(Product product);
}
