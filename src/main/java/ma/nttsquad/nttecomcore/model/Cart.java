package ma.nttsquad.nttecomcore.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart")
    @ToString.Exclude
    List<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
