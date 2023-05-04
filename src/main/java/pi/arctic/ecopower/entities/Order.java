package pi.arctic.ecopower.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.nio.MappedByteBuffer;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "`orders`")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date installationDate;
    @Enumerated(EnumType.STRING)
     OrderStatus status;

    public void setInstallationDate(LocalDate installationDate) {
        this.installationDate = java.sql.Date.valueOf(installationDate);
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    @ManyToOne
    private OrderItem orderItems;

    @OneToOne
    private Address address;



    // Getters and setters
}
