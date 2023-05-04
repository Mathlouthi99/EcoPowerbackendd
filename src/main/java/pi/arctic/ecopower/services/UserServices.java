package pi.arctic.ecopower.services;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import pi.arctic.ecopower.Security.IJwtService;
import pi.arctic.ecopower.auth.RegisterRequest;
import pi.arctic.ecopower.config.EmailExist;
import pi.arctic.ecopower.entities.User;
import pi.arctic.ecopower.repositories.UserRepo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserServices implements IUserservice {
    @Autowired
    UserRepo userRepo;
    private  final PasswordEncoder passwordEncoder;
    private final IJwtService IjwtService;



    @Override
    public void updateUser(User u) {
        if (userRepo.findById(u.getId()).isPresent())
            userRepo.save(u);
        else
            System.out.println("doesnt exist");
    }


    @Override
    public User add(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUserName())
                .completname(request.getCompletname())
                .cin(request.getCin())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .phone(request.getPhone())
                .address(request.getAddress())
                .companyname(request.getCompanyname())
                .build();
      //  if(userRepo.findByEmail(user.getEmail()).isPresent()){
        //    throw new EmailExist("email exist");}
        return         userRepo.save(user);

    }

    @Override
    public void remove(int id) {
        userRepo.delete(userRepo.findById(id).get());
    }
    @Override
    public List<User> retrieveAllUsers() {


        return  userRepo.findAll();



    }

    @Override
    public User getUserById(int id) {
        return userRepo.findById(id).get();
    }
@Override
    public User getUserByToken(@NonNull HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        jwt = authHeader.substring(7);
        userEmail = IjwtService.extractUserEmail(jwt);

        return userRepo.findByEmail(userEmail).get();
    }
    
}
