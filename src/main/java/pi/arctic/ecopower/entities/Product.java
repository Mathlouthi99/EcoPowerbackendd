package pi.arctic.ecopower.entities;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    @JsonIgnore
    private Long Id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    ProductCategory productCategory;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity",nullable = true)
    private int quantity;
}
