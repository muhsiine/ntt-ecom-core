package ma.nttsquad.nttecomcore.model;

import lombok.*;
import ma.nttsquad.nttecomcore.cons.LangCons;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "CATEGORY_X_LANG")
public class CategoryLang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private LangCons langCode;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

}
