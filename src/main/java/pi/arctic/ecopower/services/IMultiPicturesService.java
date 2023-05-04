package pi.arctic.ecopower.services;

import pi.arctic.ecopower.entities.MultiPictures;
import pi.arctic.ecopower.entities.Product;

import java.util.List;
import java.util.Set;

public interface IMultiPicturesService {
    void add(MultiPictures pictures);
    MultiPictures update(MultiPictures pictures);
    List<MultiPictures> getAll();
    MultiPictures getById(long id);
    void remove(long id);
    Set<MultiPictures> getPicturesForProduct(Product product);
}
