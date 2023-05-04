package pi.arctic.ecopower.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pi.arctic.ecopower.entities.Help;
import pi.arctic.ecopower.entities.HelpCategory;
import pi.arctic.ecopower.repositories.HelpRepo;

import java.util.List;
@Service
@AllArgsConstructor
public class HelpServiceImp implements IHelpService{
    @Autowired
    private HelpRepo helpRepo;

    @Override
    public void add(Help c) {
        helpRepo.save(c);
    }

    @Override
    public Help update(Help c) {
        return helpRepo.save(c);
    }
    @Override
    public List<Help> getAll() {
        return helpRepo.findAll();
    }

    @Override
    public Help getById(long id) {
        return helpRepo.findById(id).orElse(null);

    }

    @Override
    public void remove(long id) {
        helpRepo.deleteById(id);
    }

    @Override
    public Help MakeStatusInProgress(Help c) {
        return helpRepo.save(c);
    }

    @Override
    public Help MakeStatusDone(Help c) {
        return helpRepo.save(c);
    }
    @Override
    public Help MakeStatusRejected(Help c) {
        return helpRepo.save(c);
    }

    @Override
    public List<Help> getAllHelpsByCategory(HelpCategory category) {
        return helpRepo.findHelpByHelpCategory(category);
    }
    @Override
    public List<Help> searchHelp(String text) {
        String likeExpression = "%"+text+"%";
        if (text ==""){
            return helpRepo.findAll();
        }else
        return helpRepo.searchOfferByText(likeExpression);

    }
    /*
    @Override
    public String HelpTranslate (Long helpid) {

        Help help = helpRepo.findById(helpid).get();
        String tt = help.getDescription();
        String uri ="https://translate.hirak.site/?lang=fr-en&txt="+tt+"&token=c3e08f7822533141c2fc66df8993ca32";
        RestTemplate restTemplate = new RestTemplate();
        TextTranslate textTranslate = restTemplate.getForObject(uri,TextTranslate.class);

        return textTranslate.getResult();
    } */
}
