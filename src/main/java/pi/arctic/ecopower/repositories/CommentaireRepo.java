package pi.arctic.ecopower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.arctic.ecopower.entities.Commentaire;
import pi.arctic.ecopower.entities.Product;

import java.util.List;
import java.util.Set;

@Repository
public interface CommentaireRepo extends JpaRepository<Commentaire, Long> {
    Set<Commentaire> findByProductCommentaire(Product product);
}
//hii