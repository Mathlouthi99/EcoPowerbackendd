package pi.arctic.ecopower.controllers;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.arctic.ecopower.auth.RegisterRequest;
import pi.arctic.ecopower.entities.User;
import pi.arctic.ecopower.services.IUserservice;

import javax.persistence.PostUpdate;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
   private final IUserservice iUserservice;
    @PostMapping("/add-user")
    @Transactional

    public ResponseEntity<String> add(@RequestBody RegisterRequest u) {
        iUserservice.add(u);
        return ResponseEntity.ok("User was successfully added");

    }


@PutMapping("/update")
    @Transactional

    public ResponseEntity<String> updateUser(User u) {
        iUserservice.updateUser(u);
        return new ResponseEntity("User with '"+u.getId()+"' has been sucessfully deleted", HttpStatus.OK);
    }

    @DeleteMapping("/remove-user/{id}")
    @Transactional

    public ResponseEntity<String> remove(@PathVariable("id") Integer  id) {
        iUserservice.remove(id);
        return new ResponseEntity<String>("User with '"+id+"' has been sucessfully deleted", HttpStatus.OK);
    }


    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> listOfUsers = iUserservice.retrieveAllUsers();
        return new ResponseEntity<List<User>>(listOfUsers, HttpStatus.OK);
    }
    @GetMapping("/get-user/{id}")

    public ResponseEntity<User>  getUserById(@PathVariable("id") Integer  id) {
        User u = iUserservice.getUserById(id);
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }
}
