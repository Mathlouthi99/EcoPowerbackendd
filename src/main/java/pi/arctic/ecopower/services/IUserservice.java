package pi.arctic.ecopower.services;

import lombok.NonNull;
import pi.arctic.ecopower.auth.RegisterRequest;
import pi.arctic.ecopower.entities.Product;
import pi.arctic.ecopower.entities.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IUserservice {
     User add(RegisterRequest request);
     User getUserByToken(@NonNull HttpServletRequest request);
    void remove(int id);


    void updateUser(User u) ;
    List<User> retrieveAllUsers();
   User getUserById(int id);
}
