package pi.arctic.ecopower.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.entities.Help;
import pi.arctic.ecopower.entities.HelpCategory;
import pi.arctic.ecopower.entities.Product;
import pi.arctic.ecopower.entities.ProductCategory;
import pi.arctic.ecopower.repositories.CategoryHelpRepo;

import java.util.List;
@Service
@AllArgsConstructor
public class CategoryHelpServiceImp implements IHelpCategoryService{
    private CategoryHelpRepo categoryHelpRepo;
    @Override
    public void add(HelpCategory c) {
        categoryHelpRepo.save(c);
    }

    @Override
    public HelpCategory update(HelpCategory c) {
        return categoryHelpRepo.save(c);

    }

    @Override
    public List<HelpCategory> getAll() {
        return categoryHelpRepo.findAll();
    }

    @Override
    public HelpCategory getById(long id) {
         return categoryHelpRepo.findById(id).orElse(null);
    }

    @Override
    public void remove(long id) {
        categoryHelpRepo.deleteById(id);
    }



}
