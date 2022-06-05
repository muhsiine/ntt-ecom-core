package ma.nttsquad.nttecomcore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.nttsquad.nttecomcore.model.Category;
import ma.nttsquad.nttecomcore.model.ProductImage;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private Double price;
    private List<ProductImage> images;
    private Category category;
    private Integer nmAvailableItems;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private LocalDateTime deletedAt;

}
