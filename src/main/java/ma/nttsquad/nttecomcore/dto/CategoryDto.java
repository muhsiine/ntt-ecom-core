package ma.nttsquad.nttecomcore.dto;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CategoryDto {

    private Long id;
    private String categoryCode;
    private String icon;
    private List<LanguageDto> languages;

}