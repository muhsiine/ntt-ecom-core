package ma.nttsquad.nttecomcore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ma.nttsquad.nttecomcore.cons.LangCons;
import ma.nttsquad.nttecomcore.model.Category;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CategoryLangDto {

    @JsonIgnore
    private Long id;
    private LangCons langCode;
    private String description;


    private String categoryCode;

    private String icon;
}
