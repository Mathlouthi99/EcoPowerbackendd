package pi.arctic.ecopower.services;

import pi.arctic.ecopower.entities.*;

import java.util.List;
import java.util.Set;

public interface IHelpCategoryService {
    void add(HelpCategory c);
    HelpCategory update(HelpCategory c);
    List<HelpCategory> getAll();
    HelpCategory getById(long id);
    void remove(long id);

}
