package pi.arctic.ecopower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pi.arctic.ecopower.entities.Help;
import pi.arctic.ecopower.entities.HelpCategory;

import java.util.List;

@Repository
public interface HelpRepo extends JpaRepository<Help, Long> {
    List<Help> findHelpByHelpCategory(HelpCategory category);



    @Query("SELECT o FROM Help o "
            + "WHERE ( (lower(o.title)) like lower(CONCAT('%',:searchText,'%'))"
            + "OR lower(o.description) like lower(CONCAT('%',:searchText,'%'))) ")

    public List<Help> searchOfferByText(@Param("searchText") String searchText);
}
