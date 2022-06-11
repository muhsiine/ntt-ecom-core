package ma.nttsquad.nttecomcore.dto;

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
    private CartDto cart;
    private ProductDto product;
    private Integer quantity;
}
