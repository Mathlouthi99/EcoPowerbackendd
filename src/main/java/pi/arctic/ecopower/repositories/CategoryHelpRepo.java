package pi.arctic.ecopower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.arctic.ecopower.entities.Help;
import pi.arctic.ecopower.entities.HelpCategory;


import java.util.List;

@Repository
public interface CategoryHelpRepo extends JpaRepository<HelpCategory,Long> {

}
