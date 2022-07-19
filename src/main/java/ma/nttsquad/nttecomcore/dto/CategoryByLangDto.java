package ma.nttsquad.nttecomcore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ma.nttsquad.nttecomcore.cons.LangCons;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CategoryByLangDto {

    //@JsonIgnore
    private Long id;
    private LangCons langCode;
    private String description;


    private String categoryCode;

    private String icon;
}
