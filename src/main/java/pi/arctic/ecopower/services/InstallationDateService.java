package pi.arctic.ecopower.services;

import com.sun.istack.NotNull;
import pi.arctic.ecopower.entities.InstallationDate;
import pi.arctic.ecopower.entities.Order;
import pi.arctic.ecopower.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public interface InstallationDateService {
    void pickInstallationDate(Order order, LocalDate date);

}
