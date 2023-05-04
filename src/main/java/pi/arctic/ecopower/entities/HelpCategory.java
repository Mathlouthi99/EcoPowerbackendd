package pi.arctic.ecopower.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HelpCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore

    Long id;
    String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "helpCategory")
    @JsonIgnoreProperties("helpCategory")
    Set<Help> helps;
}
