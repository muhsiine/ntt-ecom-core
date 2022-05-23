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
    private Long addressDesc;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}