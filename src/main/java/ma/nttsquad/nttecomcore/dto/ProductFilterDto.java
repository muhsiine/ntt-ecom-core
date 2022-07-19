package ma.nttsquad.nttecomcore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFilterDto {
    private String name;
    private String description;
    private Long categoryId;
    private LocalDateTime initialDate;
    private LocalDateTime endDate;
    private Double price1;
    private Double price2;
}
