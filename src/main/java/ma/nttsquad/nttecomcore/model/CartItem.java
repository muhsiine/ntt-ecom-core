package ma.nttsquad.nttecomcore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Column(nullable = false)
    private Long quantity;


}
