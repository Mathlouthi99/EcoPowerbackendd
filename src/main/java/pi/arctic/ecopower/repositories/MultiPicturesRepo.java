package pi.arctic.ecopower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.arctic.ecopower.entities.MultiPictures;
import pi.arctic.ecopower.entities.Product;

import java.util.Set;

@Repository
public interface MultiPicturesRepo extends JpaRepository<MultiPictures,Long> {
    Set<MultiPictures> findMultiPicturesByProductimage(Product product);
}
