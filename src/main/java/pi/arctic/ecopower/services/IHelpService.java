package pi.arctic.ecopower.services;

import pi.arctic.ecopower.entities.Help;
import pi.arctic.ecopower.entities.HelpCategory;

import java.util.List;
import java.util.Set;

public interface IHelpService {
    void add(Help c);
    Help update(Help c);
    List<Help> getAll();
    Help getById(long id);
    void remove(long id);
    Help MakeStatusInProgress(Help c);
    Help MakeStatusDone(Help c);
    Help MakeStatusRejected(Help c);
    List<Help> getAllHelpsByCategory(HelpCategory category);
    List<Help> searchHelp(String text);
    //String HelpTranslate (Long helpid);

}
