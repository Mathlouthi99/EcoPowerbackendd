package pi.arctic.ecopower.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pi.arctic.ecopower.entities.Role;

import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private int cin;

    private String userName;


    private String completname;
    private String email;
    private String password;
    private int phone;
    private String address;


    private String companyname;
    private Role role;

    public boolean enabled;

}
