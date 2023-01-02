package ma.nttsquad.nttecomcore.model;

import lombok.*;
import javax.persistence.*;
import ma.nttsquad.nttecomcore.cons.LangCons;

@Entity
@Table(name = "Categories_Languages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryByLang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    @Column(length = 50)
    private String description;

    @Enumerated(EnumType.STRING)
    private LangCons langCode;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

}
