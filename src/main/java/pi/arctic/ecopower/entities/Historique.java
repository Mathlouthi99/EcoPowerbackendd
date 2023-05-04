package pi.arctic.ecopower.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.DateFormat;

@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Historique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id ;
     DateFormat DateAchat ;
    public void setOrderId(Long id) {
    }

    public void setUserId(int id) {
    }
}
