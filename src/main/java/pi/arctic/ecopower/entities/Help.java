package pi.arctic.ecopower.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String title;
    String description;
    Long idClient;
    @Column(nullable = true, length = 64)
    private String photos;
    HelpStatus status;
    @ManyToOne
    @JsonIgnoreProperties("helps")
    HelpCategory helpCategory;
}
