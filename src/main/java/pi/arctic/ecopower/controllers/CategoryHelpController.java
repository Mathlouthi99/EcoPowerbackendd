package pi.arctic.ecopower.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pi.arctic.ecopower.entities.Help;
import pi.arctic.ecopower.entities.HelpCategory;
import pi.arctic.ecopower.entities.ProductCategory;
import pi.arctic.ecopower.repositories.CategoryHelpRepo;
import pi.arctic.ecopower.repositories.HelpRepo;
import pi.arctic.ecopower.services.IHelpCategoryService;
import pi.arctic.ecopower.services.IProductCategoryService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("categoryHelp")
@AllArgsConstructor
public class CategoryHelpController {
    private IHelpCategoryService iHelpCategoryService;
    private CategoryHelpRepo categoryHelpRepo;
    private HelpRepo helpRepo;
    @PostMapping("/add")
    void add(@RequestBody HelpCategory category){
        iHelpCategoryService.add(category);
    }
    @PutMapping("/update/{id}")
    HelpCategory update(@RequestBody HelpCategory category,@PathVariable long id){
        HelpCategory hC = categoryHelpRepo.getById(id);
        hC.setName(category.getName());
        Set<Help> helps = new HashSet<Help>(helpRepo.findHelpByHelpCategory(hC));
        hC.setHelps(helps);
        return iHelpCategoryService.update(hC);
    }
    @GetMapping("/all")
    List<HelpCategory> getAll(){
        return iHelpCategoryService.getAll();
    }
    @GetMapping("/get/{id}")
    HelpCategory getById(@PathVariable long id){
        return iHelpCategoryService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable long id){
        iHelpCategoryService.remove(id);
    }
}
