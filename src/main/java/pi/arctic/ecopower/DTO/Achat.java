package pi.arctic.ecopower.DTO;

import pi.arctic.ecopower.entities.Address;
import pi.arctic.ecopower.entities.Order;
import pi.arctic.ecopower.entities.OrderItem;
import lombok.Data;
import org.apache.catalina.User;

import java.util.Set;

@Data
public class Achat {
    User user;
    private Address addressLivraison;
    private Set<OrderItem> orderItems;
    private Order order;
}

