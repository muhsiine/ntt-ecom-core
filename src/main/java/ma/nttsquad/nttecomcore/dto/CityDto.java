package ma.nttsquad.nttecomcore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {
    private Long id;
    private String name;
    private CountryDto country;
}
