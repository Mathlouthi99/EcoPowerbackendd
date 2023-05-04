package pi.arctic.ecopower.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.arctic.ecopower.entities.InstallationDate;
import pi.arctic.ecopower.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InstallationDateRepository extends JpaRepository<InstallationDate, Long> {
    Optional<InstallationDate> findByDateAndUser(LocalDate date, User user);
    InstallationDate save(InstallationDate installationDate);

}
