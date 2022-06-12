package ma.nttsquad.nttecomcore.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
