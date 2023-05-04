package pi.arctic.ecopower.DTO;

import lombok.Data;
import org.apache.catalina.User;

@Data
public class Payment {
    private User user;
    private int amount;
    private String currency;
    private String Emailrecu;

}
