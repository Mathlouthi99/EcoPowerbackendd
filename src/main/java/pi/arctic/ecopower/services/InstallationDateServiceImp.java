package pi.arctic.ecopower.services;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.arctic.ecopower.entities.InstallationDate;
import pi.arctic.ecopower.entities.Order;
import pi.arctic.ecopower.entities.Role;
import pi.arctic.ecopower.repositories.InstallationDateRepository;
import pi.arctic.ecopower.entities.User;
import pi.arctic.ecopower.repositories.OrderRepo;
import pi.arctic.ecopower.repositories.UserRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static pi.arctic.ecopower.entities.Role.Provider;

@Service
public class InstallationDateServiceImp implements InstallationDateService {

    @Autowired
    InstallationDateRepository installationDateRepository;

    @Autowired
    UserRepo userRepository;

    @Autowired
    OrderRepo orderRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    IUserservice iUserservice ;


    @Override
// Method to pick a date and store it in the Order entity
    public void pickInstallationDate(Order order, LocalDate date) {
        // Set the installation date in the Order entity
        InstallationDate installationDate = new InstallationDate();
        installationDate.setDate(date);
        installationDate.setUser(order.getUser());
        installationDate.setOrder(order);
    }








}
