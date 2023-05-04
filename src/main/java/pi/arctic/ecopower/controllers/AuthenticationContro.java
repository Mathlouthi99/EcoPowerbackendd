package pi.arctic.ecopower.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pi.arctic.ecopower.auth.RegisterRequest;
import pi.arctic.ecopower.services.AuthenticationService;
import  pi.arctic.ecopower.auth.AuthenticationResponse;
import pi.arctic.ecopower.auth.AuthenticationRequest;
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
@Qualifier("authenticationController")
public class AuthenticationContro {



     private final    AuthenticationService authenticationService;


        @PostMapping("/register")
        public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
            return ResponseEntity.ok(authenticationService.register(request));
        }

        @PostMapping("/authenticate")
        public ResponseEntity<AuthenticationResponse> authentication(@RequestBody AuthenticationRequest request){
            return ResponseEntity.ok(authenticationService.authenticate(request));
        }
    }

