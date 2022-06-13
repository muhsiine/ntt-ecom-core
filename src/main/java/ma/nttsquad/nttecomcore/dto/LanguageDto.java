package ma.nttsquad.nttecomcore.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class LanguageDto {

    private Long id;
    private String langCode;
    private String label;

}
