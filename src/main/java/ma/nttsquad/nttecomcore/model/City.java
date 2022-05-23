package ma.nttsquad.nttecomcore.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    private Long name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
