package ma.nttsquad.nttecomcore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {
    private Long id;
    private ProductDto product;
    @JsonIgnore
    private CartDto cart;
    private Integer quantity;
}
