package ee.ordermanagmentsystem.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderLineDTO {
    private Long id;
    private Long productId;
    private int quantity;
}
