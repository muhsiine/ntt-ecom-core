package ma.nttsquad.nttecomcore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private String addressDesc;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
