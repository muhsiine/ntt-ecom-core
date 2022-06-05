package ma.nttsquad.nttecomcore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private AddressDto address;
    private StatusDto status;
    private List<OrderItemDto> orderItems;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private UserDto user;
}
