package pi.arctic.ecopower.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.entities.MultiPictures;
import pi.arctic.ecopower.entities.Product;
import pi.arctic.ecopower.repositories.MultiPicturesRepo;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MultiPicturesServiceImp implements IMultiPicturesService{
    private MultiPicturesRepo multiPicturesRepo;
    @Override
    public void add(MultiPictures pictures) {
        multiPicturesRepo.save(pictures);

    }

    @Override
    public MultiPictures update(MultiPictures pictures) {
        return multiPicturesRepo.save(pictures);
    }

    @Override
    public List<MultiPictures> getAll() {
        return multiPicturesRepo.findAll();
    }

    @Override
    public MultiPictures getById(long id) {
        return multiPicturesRepo.findById(id).orElse(null);
    }

    @Override
    public void remove(long id) {
        multiPicturesRepo.deleteById(id);

    }

    @Override
    public Set<MultiPictures> getPicturesForProduct(Product product) {
        return multiPicturesRepo.findMultiPicturesByProductimage(product);
    }
}
