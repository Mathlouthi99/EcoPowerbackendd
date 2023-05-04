package pi.arctic.ecopower.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pi.arctic.ecopower.entities.Help;
import pi.arctic.ecopower.entities.HelpCategory;
import pi.arctic.ecopower.entities.HelpStatus;
import pi.arctic.ecopower.repositories.CategoryHelpRepo;
import pi.arctic.ecopower.repositories.HelpRepo;
import pi.arctic.ecopower.services.IHelpCategoryService;
import pi.arctic.ecopower.services.IHelpService;

import java.util.List;

@RestController
@RequestMapping("help")
@AllArgsConstructor
public class HelpController {
    private IHelpService iHelpService;
    private CategoryHelpRepo repoHelpCategory;
    private HelpRepo repoHelp;


    @PostMapping("/add/{CategoryId}")
    void add(@RequestBody Help help,@PathVariable(value ="CategoryId") Long CategoryId ){
        HelpCategory helpCategory = repoHelpCategory.findById(CategoryId).get();
        help.setHelpCategory(helpCategory);
        iHelpService.add(help);
    }
    @PutMapping("/update/{id}")
    Help update(@RequestBody Help help,@PathVariable long id){
        Help h = repoHelp.getById(id);
        h.setTitle(help.getTitle());
        h.setDescription(help.getDescription());
        h.setPhotos(help.getPhotos());
        h.setStatus(help.getStatus());
        return iHelpService.update(h);
    }
    @GetMapping("/all")
    List<Help> getAll(){
        return iHelpService.getAll();
    }
    @GetMapping("/get/{id}")
    Help getById(@PathVariable long id){
        return iHelpService.getById(id);
    }
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable long id){
        iHelpService.remove(id);
    }
    @GetMapping("/filterHelpByCategory/{id}")
    List<Help> getHelpByCategory(@PathVariable long id){
        HelpCategory helpCategory = repoHelpCategory.findById(id).get();
        List<Help> helps = repoHelp.findHelpByHelpCategory(helpCategory);
        return helps;
    }
    @PostMapping("/searchHelps/{search_text}")
    public List<Help> searchOffer (@PathVariable("search_text") String search_text) {
        return repoHelp.searchOfferByText(search_text);
    }
    @PutMapping("/progress/{id}")
    Help MakeStatusInProgress (@RequestBody Help help,@PathVariable long id){
        Help h = repoHelp.getById(id);
        h.setTitle(help.getTitle());
        h.setDescription(help.getDescription());
        h.setPhotos(help.getPhotos());
        h.setStatus(HelpStatus.valueOf("IN_PROGRESS"));
        return iHelpService.update(h);
    }
    @PutMapping("/done/{id}")
    Help MakeStatusDone (@RequestBody Help help,@PathVariable long id){
        Help h = repoHelp.getById(id);
        h.setTitle(help.getTitle());
        h.setDescription(help.getDescription());
        h.setPhotos(help.getPhotos());
        h.setStatus(HelpStatus.valueOf("DONE"));
        return iHelpService.update(h);
    }
    @PutMapping("/rejected/{id}")
    Help MakeStatusRejected (@RequestBody Help help,@PathVariable long id){
        Help h = repoHelp.getById(id);
        h.setTitle(help.getTitle());
        h.setDescription(help.getDescription());
        h.setPhotos(help.getPhotos());
        h.setStatus(HelpStatus.valueOf("REJECTED"));
        return iHelpService.update(h);
    }
}

