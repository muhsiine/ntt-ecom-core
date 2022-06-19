package ma.nttsquad.nttecomcore.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CategoryDto {

    private Long id;
    private String categoryCode;
    private String icon;

}