package pi.arctic.ecopower.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long idClient;
    @ManyToOne
    Product product;
    @CreationTimestamp
    LocalDate CreatedAt;
    @ManyToOne
    Product productCommentaire;

}
