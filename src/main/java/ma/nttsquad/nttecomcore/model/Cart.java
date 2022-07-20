package ma.nttsquad.nttecomcore.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Carts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @ToString.Exclude
    List<CartItem> cartItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
