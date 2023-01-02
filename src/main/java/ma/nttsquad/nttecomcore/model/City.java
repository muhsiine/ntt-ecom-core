package ma.nttsquad.nttecomcore.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "Cities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cyti_id")
    private Long id;

    @Length(max = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
